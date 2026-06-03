package com.stschool.springsecurity.exception;

public class IllegalCredentialsException extends RuntimeException {
    public IllegalCredentialsException(String message) {
        super(message);
    }
}
