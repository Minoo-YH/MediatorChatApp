package org.example;

import java.util.List;

public interface ChatMediator {

    // Register a chat client
    void registerClient(ChatClientController client);

    // Deliver a message to the selected recipient
    boolean sendMessage(String sender, String recipient, String message);

    // Get available recipients
    List<String> getRecipients(String username);
}
