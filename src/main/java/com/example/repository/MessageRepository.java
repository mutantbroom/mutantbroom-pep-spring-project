package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.Message;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    public boolean existsByPostedBy(Integer postedby);

    public List<Message> findAll();
    public Message findBymessageId(Integer messageId);
    
    public boolean existsBymessageId(Integer messageId);
    public void deleteBymessageId(Integer messageId);

    public List<Message> findByPostedBy(Integer accountId);
}
