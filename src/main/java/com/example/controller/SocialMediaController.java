package com.example.controller;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    private final AccountService accountService;
    private final MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account potentialNewAccount) {
        Account returnedAccount = accountService.register(potentialNewAccount);
        return ResponseEntity.ok(returnedAccount);
    }

    @PostMapping("login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account potentialLogin) {
        Account returnedAccount = accountService.login(potentialLogin);
        return ResponseEntity.ok(returnedAccount);
    }



    
    @PostMapping("messages")
    public ResponseEntity<Message> postMessage(@RequestBody Message potentialMessage) {
        Message returnedMessage = messageService.post(potentialMessage);
        return ResponseEntity.ok(returnedMessage);
    }



    
    @GetMapping("messages")
    public ResponseEntity<List<Message>> getMessages() {
        List<Message> returnedMessages = messageService.getAll();
        return ResponseEntity.ok(returnedMessages);
    }



    
    @GetMapping("messages/{messageId}")
    public ResponseEntity<Message> getMessage(@PathVariable int messageId) {
        Message returnedMessage = messageService.getMessage(messageId);
        return ResponseEntity.ok(returnedMessage);
    }


    
    @DeleteMapping("messages/{messageId}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable int messageId) {
        int affectedRows = messageService.delete(messageId);
        return (affectedRows == 0) ? ResponseEntity.ok().build() : ResponseEntity.ok(affectedRows);
    }


    
    @PatchMapping("messages/{messageId}")
    public ResponseEntity<Integer> patchMessage(@PathVariable int messageId, @RequestBody Message potentialPatchedMessage) {
        int affectedRows = messageService.patch(messageId, potentialPatchedMessage);
        return ResponseEntity.ok(affectedRows);
    }



    
    @GetMapping("accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getMessagesForUser(@PathVariable int accountId) {
        List<Message> returnedMessages = messageService.getByAccountId(accountId);
        return ResponseEntity.ok(returnedMessages);
    }

}
