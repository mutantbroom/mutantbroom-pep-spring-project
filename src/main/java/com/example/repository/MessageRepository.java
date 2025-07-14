package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.Message;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    Optional<Message> findMessageByMessageId(int messageId);



    @Transactional
    int deleteMessageByMessageId(int messageId);



    @Transactional
    @Modifying
    @Query("UPDATE Message SET messageText = ?1 WHERE messageId = ?2")
    int updateMessageById(String messageText, int messageId);



    
    List<Message> getMessagesByPostedBy(int accountId);
}
