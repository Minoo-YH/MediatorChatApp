package org.example;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class ChatWindow {

    private final ChatClientController controller;

    public ChatWindow(ChatClientController controller) {
        this.controller = controller;
    }

    public void show(Stage stage) {

        // Display sent and received messages
        TextArea chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setWrapText(true);

        // Select a recipient
        ComboBox<String> recipientBox = new ComboBox<>();
        recipientBox.getItems().addAll(controller.getRecipients());
        recipientBox.setPromptText("Select recipient");
        recipientBox.setMaxWidth(Double.MAX_VALUE);

        // Message input
        TextField messageField = new TextField();
        messageField.setPromptText("Type your message...");

        Button sendButton = new Button("Send");
        sendButton.setMaxWidth(Double.MAX_VALUE);

        // Update the chat area when a message arrives
        controller.setMessageListener(message ->
                chatArea.appendText(message + "\n"));

        // Handle the send button
        sendButton.setOnAction(event -> {
            String recipient = recipientBox.getValue();
            String message = messageField.getText();

            if (recipient != null && !message.isBlank()) {
                controller.sendMessage(recipient, message);
                messageField.clear();
            }
        });

        // Send a message by pressing Enter
        messageField.setOnAction(event -> sendButton.fire());

        // Create the window layout
        VBox layout = new VBox(10,
                new Label("Chat messages"),
                chatArea,
                new Label("Recipient"),
                recipientBox,
                messageField,
                sendButton);

        layout.setPadding(new Insets(15));
        VBox.setVgrow(chatArea, Priority.ALWAYS);

        // Display the username in the window title
        stage.setTitle(controller.getUsername());
        stage.setScene(new Scene(layout, 340, 420));
        stage.show();
    }
}
