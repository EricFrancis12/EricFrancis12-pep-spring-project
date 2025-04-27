package com.example.exception;

public class AccountNotFoundException extends ResourceNotFoundException {
    public AccountNotFoundException(String message) {
        super(message);
    }

    public static AccountNotFoundException fromUsername(String username) {
        return new AccountNotFoundException(String.format("Account not found with username (%s)",
                username));
    }

    public static AccountNotFoundException fromUsernameAndPassword(String username, String password) {
        return new AccountNotFoundException(String.format(
                "Account not found with username (%s) and password (%s)",
                username, password));
    }

}
