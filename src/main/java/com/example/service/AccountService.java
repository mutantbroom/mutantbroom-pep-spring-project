package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.repository.AccountRepository;
import com.example.entity.Account;
import com.example.exception.*;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account registerUser(Account account){
        if(accountRepository.existsByUsername(account.getUsername()) == true)
        {
            throw new IllegalArgumentException(); // We should catch this is the Controller. Or, better yet, should we handle this using one of the @ExceptionHandler methods to handle this exception
        }
        else
        {
            Account newAccount = accountRepository.save(account);
            return newAccount;
        }
    }

    public Account loginUser(Account account){
        if((accountRepository.existsByUsername(account.getUsername())) == false || (accountRepository.existsByPassword(account.getPassword())) == false)
        {
            throw new UnauthorizedException("Invalid Credentials");
        }
        else 
        {
            Account returnAccount = accountRepository.findByUsername(account.getUsername());
            return returnAccount;
        }
    }
}
