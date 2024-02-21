package com.kevinlam.BlogPost.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountDB accountDB;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registerAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountDB.save(account);
    }
}
