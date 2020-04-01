package com.hurriyetemlak.todoapp.todoapp.exception.exceptions;

public class EmailDoesNotValidException extends RuntimeException {
    public EmailDoesNotValidException(String message)
    {
        super(message);
    }
}
