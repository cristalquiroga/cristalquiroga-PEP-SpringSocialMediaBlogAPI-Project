package com.example.controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import java.util.List;
import java.util.Optional;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
public class SocialMediaController {
  private AccountService accountService;
  private MessageService messageService;

  public SocialMediaController(AccountService accountService, MessageService messageService){
    this.accountService = accountService;
    this.messageService = messageService;
  }

  @PostMapping("/register")
  public ResponseEntity<Account> registerAccount(@RequestBody Account account){
    try{
      Account registeredAccount = accountService.registerAccount(account);
      return ResponseEntity.ok(registeredAccount);
    } catch(IllegalStateException e){
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    } catch(IllegalArgumentException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @PostMapping("/login")
  public ResponseEntity<Account> login(@RequestBody Account account){
    try{
      Account loginAccount = accountService.login(account);
      return ResponseEntity.ok(loginAccount);
    } catch(IllegalArgumentException e){
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

  @PostMapping("/messages")
  public ResponseEntity<Message> createMessage(@RequestBody Message message){
    try{
      Message createdMessage = messageService.createMessage(message);
      return ResponseEntity.ok(createdMessage);
    } catch(IllegalArgumentException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @GetMapping("/messages")
  public ResponseEntity<List<Message>> getAllMessages(){
    List<Message> messageList = messageService.getAllMessages();
    return ResponseEntity.ok(messageList);
  }

  @GetMapping("/messages/{messageId}")
  public ResponseEntity<Message> getMessage(@PathVariable Integer messageId){
    Optional<Message> message = messageService.getMessage(messageId);
    return ResponseEntity.ok(message.orElse(null));
  }

  @DeleteMapping("/messages/{messageId}")
  public ResponseEntity<Integer> deleteMessage(@PathVariable Integer messageId){
    boolean exists = messageService.deleteMessage(messageId);
    if (exists == true)
      return ResponseEntity.ok(1);
    else
      return ResponseEntity.ok().build();
  }

  @PatchMapping("/messages/{messageId}")
  public ResponseEntity<Integer> updateMessage(@PathVariable Integer messageId, @RequestBody Message message){
    try{
      messageService.updateMessage(messageId, message.getMessageText());
      return ResponseEntity.ok(1);
    } catch (IllegalArgumentException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }
}