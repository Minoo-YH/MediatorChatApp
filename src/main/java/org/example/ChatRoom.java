package org.example;

import java.util.*;

public class ChatRoom implements ChatMediator {

    // Store all registered clients
    private final Map<String, ChatClientController> clients =
            new LinkedHashMap<>();

    @Override
    public void registerClient(ChatClientController client) {
        if (clients.containsKey(client.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        clients.put(client.getUsername(), client);
    }

    @Override
    public boolean sendMessage(String sender, String recipient, String message) {

        // Find the recipient and deliver the message
        ChatClientController receiver = clients.get(recipient);

        if (receiver == null || !clients.containsKey(sender)) {
            return false;
        }

        receiver.receiveMessage(sender, message);
        return true;
    }

    @Override
    public List<String> getRecipients(String username) {

        // Exclude the current user from the recipient list
        List<String> recipients = new ArrayList<>(clients.keySet());
        recipients.remove(username);
        return recipients;
    }
}
