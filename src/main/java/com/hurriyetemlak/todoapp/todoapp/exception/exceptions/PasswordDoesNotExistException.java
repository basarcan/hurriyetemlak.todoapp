package com.hurriyetemlak.todoapp.todoapp.exception.exceptions;

public class PasswordDoesNotExistException extends RuntimeException {
    public PasswordDoesNotExistException(String message)
    {
        super(message);
    }
}
