package com.kevinlam.BlogPost.Users;

import com.kevinlam.BlogPost.Exceptions.PasswordIncorrectException;
import com.kevinlam.BlogPost.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountDB accountDB;

    private BCryptPasswordEncoder passwordEncoder;

    public void registerAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountDB.save(account);
    }

    public boolean checkLoginCredentials(String username, String password) {
        Account account = accountDB.findByUsername(username);
        if (account == null) { throw new UserNotFoundException("There is not an account with this username."); }
        if (!passwordEncoder.matches(password, account.getPassword())) { throw new PasswordIncorrectException("The password is incorrect for the username."); }
        return true;
    }
}
