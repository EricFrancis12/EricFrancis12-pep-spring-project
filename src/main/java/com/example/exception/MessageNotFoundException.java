package com.example.exception;

public class MessageNotFoundException extends ResourceNotFoundException {
    public MessageNotFoundException(String message) {
        super(message);
    }

    public static MessageNotFoundException fromId(int messageId) {
        return new MessageNotFoundException(String.format("Message not found with ID (%d)", messageId));
    }

}
