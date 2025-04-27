package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.AccountNotFoundException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account insertAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountByUsername(String username) throws AccountNotFoundException {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> AccountNotFoundException.fromUsername(username));
    }

    public Account getAccountByUsernameAndPassword(String username, String password) throws AccountNotFoundException {
        return accountRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> AccountNotFoundException
                        .fromUsernameAndPassword(username, password));
    }

    public boolean usernameExists(String username) {
        return !accountRepository.findByUsername(username).isEmpty();
    }
}
