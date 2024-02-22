package com.kevinlam.BlogPost.Users;

import com.kevinlam.BlogPost.Exceptions.PasswordIncorrectException;
import com.kevinlam.BlogPost.Exceptions.UserAlreadyExistsException;
import com.kevinlam.BlogPost.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("blogpost/account")
@CrossOrigin
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
        catch (UserNotFoundException e) { return ResponseEntity.status(435).body(e.getMessage()); }
        catch (PasswordIncorrectException e) { return ResponseEntity.status(436).body(e.getMessage()); }
        return ResponseEntity.ok("User logged in successfully");
    }

}
