package com.kevinlam.BlogPost.Users;

import com.kevinlam.BlogPost.Exceptions.PasswordIncorrectException;
import com.kevinlam.BlogPost.Exceptions.UserAlreadyExistsException;
import com.kevinlam.BlogPost.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountDB accountDB;

    public void registerAccount(Account account) {
        if (accountDB.findByUsername(account.getUsername()) != null) { throw new UserAlreadyExistsException(); }
        account.setPassword(PasswordEncoder.hashPassword(account.getPassword()));
        account.setUsername(account.getUsername().toLowerCase());
        accountDB.save(account);
    }

    public void checkLoginCredentials(String username, String password) {
        Account account = accountDB.findByUsername(username.toLowerCase());
        if (account == null) { throw new UserNotFoundException("There is not an account with this username."); }
        if (!PasswordEncoder.verifyPassword(password, account.getPassword())) { throw new PasswordIncorrectException("The password is incorrect for the username."); }
    }
}
