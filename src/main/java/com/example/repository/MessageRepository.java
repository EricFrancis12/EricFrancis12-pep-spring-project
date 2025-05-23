package com.example.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Message m SET m.messageText = :messageText WHERE m.messageId = :messageId")
    int updateMessageTextById(@Param("messageId") int messageId, @Param("messageText") String messageText);

    @Query("FROM Message m WHERE m.postedBy = :accountId")
    List<Message> getMessagesByAccountId(@Param("accountId") int accountId);

    @Query("SELECT COUNT(a) > 0 FROM Account a WHERE a.accountId = :accountId")
    boolean accountIdExists(@Param("accountId") int accountId);

}
