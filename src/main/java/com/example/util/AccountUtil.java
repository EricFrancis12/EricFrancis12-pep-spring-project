package com.example.util;

import com.example.entity.Account;

public class AccountUtil {

    public static final int MIN_ACCOUNT_PASSWORD_LENGTH = 4;

    public static boolean isValidUsername(String username) {
        return username != null && !username.isEmpty();
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= MIN_ACCOUNT_PASSWORD_LENGTH;
    }

    public static boolean isValidAccount(Account account) {
        return account != null
                && isValidUsername(account.getUsername())
                && isValidPassword(account.getPassword());
    }

}
