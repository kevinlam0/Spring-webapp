package com.kevinlam.BlogPost.Users;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

//    public static void main(String[] args) {
//        String password = "password123";
//        String hashedPassword = hashPassword(password);
//        System.out.println("Hashed password: " + hashedPassword);
//
//        // Verify password
//        boolean isValid = verifyPassword(password, hashedPassword);
//        System.out.println("Password is valid: " + isValid);
//    }
}