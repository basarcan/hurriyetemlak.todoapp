package com.hurriyetemlak.todoapp.todoapp.service;


import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Service
public class EncryptionService {

    public String encodeData(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public String generateToken() {
        Date date = new Date();
        long time = date.getTime();
        return Base64.getEncoder().encodeToString(Long.toString(time).getBytes());
    }
}
