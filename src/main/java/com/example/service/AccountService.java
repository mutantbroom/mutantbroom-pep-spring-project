package com.example.service;

import com.example.entity.Account;


public interface AccountService {


    Account register(Account potentialNewAccount);


    Account login(Account potentialLogin);
}
