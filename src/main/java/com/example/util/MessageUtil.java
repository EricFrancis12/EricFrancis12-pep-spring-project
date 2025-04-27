package com.example.util;

import com.example.entity.Message;

public class MessageUtil {

    public static final int MAX_MESSAGE_TEXT_LENGTH = 255;

    public static boolean isValidMessageText(String messageText) {
        return !messageText.isEmpty() && messageText.length() <= MAX_MESSAGE_TEXT_LENGTH;
    }

    public static boolean isValidMessage(Message message) {
        return message != null && isValidMessageText(message.getMessageText());
    }
}
