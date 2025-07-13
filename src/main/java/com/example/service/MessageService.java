package com.example.service;

import com.example.entity.Message;

import java.util.List;

public class MessageService {
    Message post(Message potentialMessage);



    List<Message> getAll();




    Message getMessage(int messageId);



    int delete(int messageId);




    int patch(int messageId, Message potentialPatchedMessage);



    List<Message> getByAccountId(int accountId);

}
