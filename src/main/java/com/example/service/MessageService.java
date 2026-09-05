package com.example.service;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
  private final MessageRepository messageRepository;

  public MessageService(MessageRepository messageRepository){
    this.messageRepository = messageRepository;
  }

  public Message createMessage(Message message){
    if(message.getMessageText().isBlank() || message.getMessageText().length() > 255 || messageRepository.existsById(message.getPostedBy()) == false)
      throw new IllegalArgumentException();
    return messageRepository.save(message);
  }

  public List<Message> getAllMessages(){
    return messageRepository.findAll();
  }

  public Optional<Message> getMessage(Integer messageId){
    return messageRepository.findById(messageId);
  }

  public boolean deleteMessage(Integer messageId){
    if(messageRepository.existsById(messageId) == true){
      messageRepository.deleteById(messageId);
      return true;
    }
    return false;
  }

  public void updateMessage(Integer messageId, String messageText){
    Optional<Message> optionalMessage = messageRepository.findById(messageId);
    if(optionalMessage.isEmpty() == false){
      Message message = optionalMessage.get();
      if(messageText.isBlank() || messageText.length() > 255)
        throw new IllegalArgumentException();
      message.setMessageText(messageText);
      messageRepository.save(message);
      return;
    }
    throw new IllegalArgumentException();
  }

  public List<Message> getAllMessagesByPostedBy(Integer postedBy){
    return messageRepository.findByPostedBy(postedBy);
  }
}
