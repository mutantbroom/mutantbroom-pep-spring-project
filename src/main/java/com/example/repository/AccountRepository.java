package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{ 
    Account findByUsername(String name);
    boolean existsByUsername(String username); 
    boolean existsByPassword(String password); 
}
