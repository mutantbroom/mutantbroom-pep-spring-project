package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{ 
    Account findByUsername(String name); // Optional introduced in Java 8. Instead of checking if the return value is null, we can use Optional
    boolean existsByUsername(String username); // Here we are able to use the existsBy{entity field}. The @Entity and @Column annotations in the entity class help define this capability
    boolean existsByPassword(String password); // We are leveraging Spring Data JPA here again. 
}
