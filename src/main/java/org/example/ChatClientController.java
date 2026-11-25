package org.example;

import java.util.List;
import java.util.function.Consumer;

public class ChatClientController {

    private final String username;
    private final ChatMediator mediator;

    // Callback for updating the user interface
    private Consumer<String> messageListener = message -> {};

    public ChatClientController(String username, ChatMediator mediator) {
        this.username = username;
        this.mediator = mediator;
        mediator.registerClient(this);
    }

    public String getUsername() {
        return username;
    }

    public List<String> getRecipients() {
        return mediator.getRecipients(username);
    }

    public void setMessageListener(Consumer<String> listener) {
        this.messageListener = listener;
    }

    // Send messages through the mediator
    public void sendMessage(String recipient, String message) {
        if (recipient == null || message == null || message.isBlank()) {
            return;
        }

        if (mediator.sendMessage(username, recipient, message)) {
            messageListener.accept("To " + recipient + ": " + message);
        } else {
            messageListener.accept("Message could not be delivered.");
        }
    }

    // Receive messages from the mediator
    public void receiveMessage(String sender, String message) {
        messageListener.accept("From " + sender + ": " + message);
    }
}
