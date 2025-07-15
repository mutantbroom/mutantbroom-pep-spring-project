package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    public boolean existsByPostedBy(Integer postedby);
    public List<Message> findByPostedBy(Integer accountId);
    public List<Message> findAll();
    public Message findBymessageId(Integer messageId);
    public boolean existsBymessageId(Integer messageId);
    public void deleteBymessageId(Integer messageId);
}
