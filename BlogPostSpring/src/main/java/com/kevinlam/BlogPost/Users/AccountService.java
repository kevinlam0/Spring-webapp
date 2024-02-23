package com.kevinlam.BlogPost.Users;

import com.kevinlam.BlogPost.Exceptions.InvalidUserNameException;
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
        if (account.getUsername().length() > 20) {
            throw new InvalidUserNameException("The username cannot be longer than 20 characters.");
        }
        if (account.getPassword().length() < 5) { throw new IllegalArgumentException(); }
        account.setUsername(account.getUsername().toLowerCase());

        if (accountDB.findByUser(account.getUsername()) != null || account.getUsername().equals("guest")) {
            throw new UserAlreadyExistsException();
        }

        account.setPassword(PasswordEncoder.hashPassword(account.getPassword()));
        accountDB.save(account);
    }

    public void checkLoginCredentials(String username, String password) {
        Account account = accountDB.findByUser(username.toLowerCase());
        if (account == null) { throw new UserNotFoundException("There is not an account with this username."); }
        if (!PasswordEncoder.verifyPassword(password, account.getPassword())) { throw new PasswordIncorrectException("The password is incorrect for the username."); }
    }
}
