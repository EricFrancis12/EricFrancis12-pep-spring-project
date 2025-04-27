package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.AccountNotFoundException;
import com.example.exception.MessageNotFoundException;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.util.AccountUtil;
import com.example.util.MessageUtil;

/**
 * You will need to write your own endpoints and handlers for your
 * controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use
 * the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations.
 * You should
 * refer to prior mini-project labs and lecture materials for guidance on how a
 * controller may be built.
 */
@RestController
public class SocialMediaController {

    private final AccountService accountService;
    private final MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> handleRegister(@RequestBody Account account) {
        if (!AccountUtil.isValidAccount(account)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (accountService.usernameExists(account.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Account createdAccount = accountService.insertAccount(account);
        return ResponseEntity.ok(createdAccount);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> handleLogin(@RequestBody Account account) {
        try {
            Account foundAccount = accountService.getAccountByUsernameAndPassword(account.getUsername(),
                    account.getPassword());
            return ResponseEntity.ok(foundAccount);

        } catch (AccountNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> handleCreateMessage(@RequestBody Message message) {
        if (!MessageUtil.isValidMessage(message)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            Message createdMessage = messageService.insertMessage(message);
            return ResponseEntity.ok(createdMessage);

        } catch (AccountNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> handleGetAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> handleGetMessageById(@PathVariable("messageId") int messageId) {
        try {
            Message message = messageService.getMessageById(messageId);
            return ResponseEntity.ok(message);
        } catch (MessageNotFoundException ex) {
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> handleDeleteMessageById(@PathVariable("messageId") int messageId) {
        try {
            messageService.deleteMessageById(messageId);
            return ResponseEntity.ok(1);
        } catch (MessageNotFoundException ex) {
            return ResponseEntity.ok().build();
        }
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> handleUpdateMessageById(@PathVariable("messageId") int messageId,
            @RequestBody Message message) {
        String messageText = message.getMessageText();
        if (!MessageUtil.isValidMessageText(messageText)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            messageService.updateMessageTextById(messageId, messageText);
            return ResponseEntity.ok(1);
        } catch (MessageNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> handleGetMessagesByAccountId(@PathVariable("accountId") int accountId) {
        List<Message> messages = messageService.getMessagesByAccountId(accountId);
        return ResponseEntity.ok(messages);
    }

}