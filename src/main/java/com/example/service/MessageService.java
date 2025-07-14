package com.example.service;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.ClientErrorException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    AccountRepository accountRepository; // We are bringing this in so we can check if the postedby Id with the message has a valid id for a user
    // The intial JSON will likely contain a message_text and a posted_by, I'm assuming, so that we can verify that posted by is an actual user in the system
    public Message addMessage(Message message)
    {
        // The initial constraints are already in annotaion on entity
        // Need to check the posted_by with a existsById
        if (message.getMessageText() == "") {
            throw new ClientErrorException("Message can't be blank");
        }
        if(message.getMessageText().length() > 255)
        {
            throw new ClientErrorException("Message can't be over 255 characters");
        }
        if(accountRepository.existsById(message.getPostedBy()) == true)
        {
            Message newMes = messageRepository.save(message);
            return newMes;
        }
        else
        {
            throw new ClientErrorException("Could not find associated postby id");
        }

        // We also need to, upon ensuring postedby is true, the message is persisted to db using save
    }

    public List<Message> findAllMessages()
    {
        return messageRepository.findAll();
    }

    public Message findMessageById(Integer messageId)
    {
        return messageRepository.findBymessageId(messageId);
    }

    @Transactional
    public Integer deleteMessageById(Integer messageId)
    {
        if(messageRepository.existsBymessageId(messageId))
        {
            messageRepository.deleteBymessageId(messageId);
            return 1;
        }
        else
        {
            return 0;
        }
    }

    @Transactional 
    public Integer updateMessageById(Integer messageId, Message message)
    {
        if(message.getMessageText() == "")
        {
            throw new ClientErrorException("You are dead wrong for that.");
        }
        if (message.getMessageText().length() > 255) {
            throw new ClientErrorException("You are dead wrong for that.");
        }
        if(!messageRepository.existsById(messageId))
        {
            throw new ClientErrorException("You are dead wrong for that.");
        }
        else
        {
            Message newMessage = messageRepository.findById(messageId).orElseThrow(() -> new ClientErrorException("Cmon son"));
            newMessage.setMessageText(message.getMessageText());
            return 1;
        }
    }

    public List<Message> getListOfMessagesForIndividualUser(Integer accountId)
    {
        List<Message> messageList = messageRepository.findByPostedBy(accountId);
        return messageList;
    }


}
