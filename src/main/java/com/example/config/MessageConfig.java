package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageConfig {

    @Value("${message.max-text-length:255}")
    private static int MAX_TEXT_LENGTH;

    public static int getMaxTextLength() {
        return MAX_TEXT_LENGTH;
    }

}
