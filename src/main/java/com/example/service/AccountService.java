package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.repository.AccountRepository;
import com.example.entity.Account;
import com.example.exception.*;

@Service
public class AccountService {

 @Autowired
    AccountRepository accountRepository;


    //  The response status should be 200 OK, which is the default. The new account should be persisted to the database.
    //  If the registration is not successful due to a duplicate username, the response status should be 409. (Conflict)
    //  If the registration is not successful for some other reason, the response status should be 400. (Client error)

    // 3. The registration will be successful if and only if the username is not blank
    // 4. The registration will be successful if the password is at least 4 characters long
    // 5. The registration will be successful if the Account with that username does not already exist. 

    // This is going to contain a response body of a JSON account, so I'm unsure whether or not we handle this with ResponseBody in Controller or what
    // We need to return an account which will be apart of the ResponseBody in the controller
    // We have already covered the minimum password length using jakarta in the model annotation.
    // We have already covered the username cannot be blank using jakarta annotation in the model

    // We still probably need to cover that the username doesn't exist here:
    public Account registerUser(Account account)
    {
        if(accountRepository.existsByUsername(account.getUsername()) == true)
        {
            throw new IllegalArgumentException(); // We should catch this is the Controller. Or, better yet, should we handle this using one of the @ExceptionHandler methods to handle this exception
        }
        else
        {
            Account newAccount = accountRepository.save(account);
            return newAccount;
        }
        // We could handle the response in the controller using ResponseEntity
    }

    public Account loginUser(Account account)
    {
        if((accountRepository.existsByUsername(account.getUsername())) == false || (accountRepository.existsByPassword(account.getPassword())) == false)
        {
            throw new UnauthorizedException("Invalid Credentials");
        }
        else 
        {
            // Right here we need to getIdByUsername and set that into a a new account variable along with the username and password and then return that account instance
            Account returnAccount = accountRepository.findByUsername(account.getUsername());
            return returnAccount;
        }
    }
}
