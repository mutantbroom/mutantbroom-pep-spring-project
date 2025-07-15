package com.example.service;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.ClientException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    AccountRepository accountRepository;
    public Message addMessage(Message message){
        if (message.getMessageText() == "") {
            throw new ClientException("Blank input");
        }
        if(message.getMessageText().length() > 255)
        {
            throw new ClientException("Over 255 characters");
        }
        if(accountRepository.existsById(message.getPostedBy()) == true)
        {
            Message newMes = messageRepository.save(message);
            return newMes;
        }
        else
        {
            throw new ClientException("Could not find postby id");
        }
    }

    public List<Message> findAllMessages(){
        return messageRepository.findAll();
    }

    public Message findMessageById(Integer messageId){
        return messageRepository.findBymessageId(messageId);
    }

    @Transactional
    public Integer deleteMessageById(Integer messageId){
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
    public Integer updateMessageById(Integer messageId, Message message){
        if(message.getMessageText() == "")
        {
            throw new ClientException("Update Message error");
        }
        if (message.getMessageText().length() > 255) {
            throw new ClientException("Update Message error");
        }
        if(!messageRepository.existsById(messageId))
        {
            throw new ClientException("Update Message error");
        }
        else
        {
            Message newMessage = messageRepository.findById(messageId).orElseThrow(() -> new ClientException("Cmon son"));
            newMessage.setMessageText(message.getMessageText());
            return 1;
        }
    }

    public List<Message> getListOfMessagesForIndividualUser(Integer accountId){
        List<Message> messageList = messageRepository.findByPostedBy(accountId);
        return messageList;
    }


}
