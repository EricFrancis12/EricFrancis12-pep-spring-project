package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(value = "FROM account WHERE username = :username", nativeQuery = true)
    Optional<Account> findByUsername(@Param("username") String username);

    @Query(value = "FROM account WHERE username = :username AND password = :password", nativeQuery = true)
    Optional<Account> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
