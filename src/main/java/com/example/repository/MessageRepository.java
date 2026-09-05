package com.example.repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Message;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
  public List<Message> findByPostedBy(Integer postedBy);
}
