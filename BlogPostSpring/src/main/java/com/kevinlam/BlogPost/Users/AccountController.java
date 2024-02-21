package com.kevinlam.BlogPost.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountDB accountDB;
    @Autowired
    private AccountService accountService;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Account account) {
        // Check if the username is already taken
        if (accountDB.findByUsername(account.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        // Register the user
        accountService.registerAccount(account);

        return ResponseEntity.ok("User registered successfully");
    }
}
