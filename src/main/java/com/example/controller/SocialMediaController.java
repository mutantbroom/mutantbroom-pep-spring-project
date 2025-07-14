package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.ClientErrorException;
import com.example.service.AccountService;
import com.example.service.MessageService;

import java.util.List;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @Autowired 
    AccountService accountService;

    @Autowired
    MessageService messageService;

    @PostMapping("/register")
    public ResponseEntity<Account> registerUser(@RequestBody Account account) // We forgot to add the @ResponseBody annotation in the method signature
    {
        
        // In order to use an account instance, we need to set up Dependency Injection for it. Do we need to setup a Bean for this?
        // So Spring automatically created a bean for services that are annotated with @Service, so all we have to do is AutoWire

         Account newAccount = accountService.registerUser(account);
         // If we need to return a @ResponseBody, then we need use HttpRequest along with a generic status
         return ResponseEntity.ok(newAccount);

    }

    @PostMapping("/login")
    public ResponseEntity<Account> loginUser(@RequestBody Account account)
    {
        Account validAccount = accountService.loginUser(account);
        return ResponseEntity.ok(validAccount);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> newMessage(@RequestBody Message message)
    {
        Message goodMessage = messageService.addMessage(message);
        return ResponseEntity.ok(goodMessage);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages()
    {
        List<Message> allMessages = messageService.findAllMessages();
        return ResponseEntity.ok(allMessages);
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId)
    {
        Message message = messageService.findMessageById(messageId);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<?> deleteMessageById(@PathVariable("messageId") Integer messageId)
    { // In this case, we used what is known as a wildcard in Java generics
        Integer inty = messageService.deleteMessageById(messageId);
        if(inty == 1)
        {
            return ResponseEntity.ok(inty);
        }
        else
        {
            return ResponseEntity.ok().build();
        }

        // I ran into a good little problem here. Good lesson. In spring, database transactions that deal with modifying the original state of the table
        // need to be annotated with the @Transactional annotation. This operation is mapped by EntityManager.
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<?> updateMessageById(@PathVariable("messageId") Integer messageId, @RequestBody Message message)
    {
            Integer returnInt = messageService.updateMessageById(messageId, message);
            return ResponseEntity.ok(returnInt);
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByUserAccountId(@PathVariable("accountId") Integer accountId)
    {
        List<Message> messageList = messageService.getListOfMessagesForIndividualUser(accountId);
        return ResponseEntity.ok(messageList);
    }

}
