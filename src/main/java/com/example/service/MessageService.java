package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.MessageNotFoundException;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message insertMessage(Message message) {
        return this.messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return this.messageRepository.findAll();
    }

    public Message getMessageById(int messageId) throws MessageNotFoundException {
        return this.messageRepository.findById(messageId)
                .orElseThrow(() -> MessageNotFoundException.fromId(messageId));
    }

    public List<Message> getMessagesByAccountId(int accountId) {
        return this.messageRepository.getMessagesByAccountId(accountId);
    }

    public Message updateMessageTextById(int messageId, String messageText) throws MessageNotFoundException {
        int n = this.messageRepository.updateMessageTextById(messageId, messageText);
        if (n == 0) {
            throw MessageNotFoundException.fromId(messageId);
        }
        return this.getMessageById(messageId);
    }

    public Message deleteMessageById(int messageId) throws MessageNotFoundException {
        Optional<Message> optionalMessage = this.messageRepository.findById(messageId);
        if (optionalMessage.isEmpty()) {
            throw MessageNotFoundException.fromId(messageId);
        }

        this.messageRepository.deleteById(messageId);
        return optionalMessage.get();
    }
}
