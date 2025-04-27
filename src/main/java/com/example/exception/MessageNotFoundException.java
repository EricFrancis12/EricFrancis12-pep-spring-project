package com.example.exception;

public class MessageNotFoundException extends ResourceNotFoundException {
    public MessageNotFoundException(String message) {
        super(message);
    }

    public static MessageNotFoundException fromId(int messageId) {
        return (MessageNotFoundException) ResourceNotFoundException.format("Message not found with ID (%d)",
                messageId);
    }

}
