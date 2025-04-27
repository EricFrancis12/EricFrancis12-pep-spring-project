package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query(value = "FROM message m UPDATE m.messageText = :messageText WHERE m.messageId = :messageId", nativeQuery = true)
    Optional<Message> updateMessageTextById(@Param("messageId") int messageId,
            @Param("messageText") String messageText);

    @Query(value = "FROM message m WHERE m.postedBy = :accountId", nativeQuery = true)
    List<Message> getMessagesByAccountId(@Param("accountId") int accountId);

}
