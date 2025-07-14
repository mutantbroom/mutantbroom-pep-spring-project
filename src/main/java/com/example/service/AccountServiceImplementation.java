package com.example.service;

import com.example.entity.Account;
import com.example.exception.AccountAlreadyExistsException;
import com.example.exception.LoginException;
import com.example.exception.RegistrationException;
import com.example.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImplementation implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImplementation(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }



    @Override
    public Account register(Account potentialNewAccount) {
        if (usernameIsBlank(potentialNewAccount)) throw new RegistrationException("Username is required");
        if (passwordIsToShort(potentialNewAccount)) throw new RegistrationException("Password is to short");
        if (userAlreadyExists(potentialNewAccount)) throw new AccountAlreadyExistsException("Account already exists");

        return accountRepository.save(potentialNewAccount);
    }



    @Override
    public Account login(Account potentialLogin) {
        if (usernameOrPasswordIsNull(potentialLogin)) throw new LoginException(
                "Username and password are required for a login attempt"
        );
        Optional<Account> optionalAccount = accountRepository
                .findByUsernameAndPassword(potentialLogin.getUsername(), potentialLogin.getPassword());
        return optionalAccount.orElseThrow(() -> new LoginException("Unauthorized"));
    }



    private boolean userAlreadyExists(Account potentialNewAccount) {
        return accountRepository.existsByUsername(potentialNewAccount.getUsername());
    }

    private boolean passwordIsToShort(Account potentialNewAccount) {
        return potentialNewAccount.getPassword() == null ||
                potentialNewAccount.getPassword().isEmpty() ||
                potentialNewAccount.getPassword().length() < 4;
    }

    private boolean usernameIsBlank(Account potentialNewAccount) {
        return potentialNewAccount.getUsername().isBlank();
    }

    private boolean usernameOrPasswordIsNull(Account potentialLogin) {
        return potentialLogin.getUsername() == null || potentialLogin.getPassword() == null;
    }
    
}
