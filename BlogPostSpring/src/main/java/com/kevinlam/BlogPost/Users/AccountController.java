package com.kevinlam.BlogPost.Users;

import com.kevinlam.BlogPost.Exceptions.PasswordIncorrectException;
import com.kevinlam.BlogPost.Exceptions.UserAlreadyExistsException;
import com.kevinlam.BlogPost.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("blogpost/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Account account) {
        try {accountService.registerAccount(account);}
        catch (UserAlreadyExistsException e) { return ResponseEntity.badRequest().body("Username is already taken"); }
        return ResponseEntity.ok("User registered successfully");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        try {accountService.checkLoginCredentials(account.getUsername(), account.getPassword());}
        catch (UserNotFoundException e) { return ResponseEntity.badRequest().body("There is no account with this username. "); }
        catch (PasswordIncorrectException e) { return ResponseEntity.badRequest().body("Password incorrect. "); }
        return ResponseEntity.ok("User logged in successfully");
    }

}
