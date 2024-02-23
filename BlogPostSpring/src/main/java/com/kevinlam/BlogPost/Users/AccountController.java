package com.kevinlam.BlogPost.Users;

import com.kevinlam.BlogPost.Exceptions.InvalidUserNameException;
import com.kevinlam.BlogPost.Exceptions.PasswordIncorrectException;
import com.kevinlam.BlogPost.Exceptions.UserAlreadyExistsException;
import com.kevinlam.BlogPost.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
        catch (UserAlreadyExistsException e) { return ResponseEntity.status(437).body(""); }
        catch (InvalidUserNameException e) { return ResponseEntity.status(438).body(""); }
        catch (IllegalArgumentException e) { return ResponseEntity.status(439).body(""); }
        return ResponseEntity.ok("");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        try {accountService.checkLoginCredentials(account.getUsername(), account.getPassword());}
        catch (UserNotFoundException e) { return ResponseEntity.status(435).body(""); }
        catch (PasswordIncorrectException e) { return ResponseEntity.status(436).body(""); }
        return ResponseEntity.ok("");
    }

}
