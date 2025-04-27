package com.example.util;

import com.example.config.AccountConfig;
import com.example.entity.Account;

public class AccountUtil {

    public static boolean isValidUsername(String username) {
        return username != null && !username.isEmpty();
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= AccountConfig.getMinPasswordLength();
    }

    public static boolean isValidAccount(Account account) {
        return account != null
                && isValidUsername(account.getUsername())
                && isValidPassword(account.getPassword());
    }

}
