package com.example.javabeadando.service;

import com.example.javabeadando.model.Message;
import com.example.javabeadando.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {



    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message save(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }
    public List<Message> findAllMessagesOrdered() {
        List<Message> messages = messageRepository.findAll();
        messages.sort((m1, m2) -> m2.getCreatedAt().compareTo(m1.getCreatedAt()));
        return messages;
    }
}
