package com.example.util;

import com.example.config.MessageConfig;
import com.example.entity.Message;

public class MessageUtil {

    public static boolean isValidMessageText(String messageText) {
        return !messageText.isEmpty() && messageText.length() <= MessageConfig.getMaxTextLength();
    }

    public static boolean isValidMessage(Message message) {
        return message != null && isValidMessageText(message.getMessageText());
    }

}
