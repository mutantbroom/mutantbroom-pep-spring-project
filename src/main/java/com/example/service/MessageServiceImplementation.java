package com.example.service;

import com.example.entity.Message;
import com.example.exception.MessageFormatException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImplementation implements MessageService {
    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;

    public MessageServiceImplementation(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }



    @Override
    public Message post(Message potentialMessage) {
        if (messageIsBlank(potentialMessage)) throw new MessageFormatException("A message is required");
        if (messageIsToLong(potentialMessage)) throw new MessageFormatException("Message body is to long");
        if (messagePostedByUnknownUser(potentialMessage)) throw new MessageFormatException("Unknown user");
        return messageRepository.save(potentialMessage);
    }



    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }



    @Override
    public Message getMessage(int messageId) {
        return messageRepository.findMessageByMessageId(messageId).orElse(null);
    }



    @Override
    public int delete(int messageId) {
        return messageRepository.deleteMessageByMessageId(messageId);
    }



    @Override
    public int patch(int messageId, Message potentialPatchedMessage) {
        if (messageIdNotExist(messageId)) throw new MessageFormatException("Message does not exist");
        if (messageIsBlank(potentialPatchedMessage)) throw new MessageFormatException("A message is required");
        if (messageIsToLong(potentialPatchedMessage)) throw new MessageFormatException("Message body is to long");
        return messageRepository.updateMessageById(potentialPatchedMessage.getMessageText(), messageId);
    }



    @Override
    public List<Message> getByAccountId(int accountId) {
        return messageRepository.getMessagesByPostedBy(accountId);
    }



    private boolean messagePostedByUnknownUser(Message potentialMessage) {
        return !accountRepository.existsByAccountId(potentialMessage.getPostedBy());
    }

    private boolean messageIsToLong(Message potentialMessage) {
        return potentialMessage.getMessageText().length() > 255;
    }

    private boolean messageIsBlank(Message potentialMessage) {
        return potentialMessage.getMessageText() == null || potentialMessage.getMessageText().isBlank();
    }

    private boolean messageIdNotExist(Integer messageId) {
        return !messageRepository.existsById(messageId);
    }
    
}
