package com.example.config;

import org.springframework.beans.factory.annotation.Value;

public class AccountConfig {

    @Value("${account.min-password-length:4}")
    private static int MIN_PASSWORD_LENGTH;

    public static int getMinPasswordLength() {
        return MIN_PASSWORD_LENGTH;
    }

}
