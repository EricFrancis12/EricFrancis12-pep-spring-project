package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("FROM Account a WHERE a.username = :username")
    Optional<Account> findByUsername(@Param("username") String username);

    @Query("FROM Account a WHERE a.username = :username AND a.password = :password")
    Optional<Account> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
