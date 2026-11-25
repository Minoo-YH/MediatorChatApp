package org.example;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Create one shared mediator
        ChatMediator mediator = new ChatRoom();

        // Create three independent clients
        ChatClientController alice =
                new ChatClientController("Alice", mediator);

        ChatClientController bob =
                new ChatClientController("Bob", mediator);

        ChatClientController charlie =
                new ChatClientController("Charlie", mediator);

        // Create separate chat windows
        Stage bobStage = new Stage();
        Stage charlieStage = new Stage();

        new ChatWindow(alice).show(primaryStage);
        new ChatWindow(bob).show(bobStage);
        new ChatWindow(charlie).show(charlieStage);

        // Position the windows next to each other
        primaryStage.setX(20);
        primaryStage.setY(100);

        bobStage.setX(380);
        bobStage.setY(100);

        charlieStage.setX(740);
        charlieStage.setY(100);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
