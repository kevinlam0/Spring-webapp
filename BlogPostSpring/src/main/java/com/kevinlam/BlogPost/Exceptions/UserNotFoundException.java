package com.kevinlam.BlogPost.Exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {super(message);}
}
