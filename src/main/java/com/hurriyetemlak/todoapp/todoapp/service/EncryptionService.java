package com.hurriyetemlak.todoapp.todoapp.service;


import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class EncryptionService {

    public String encodeData(String password)
    {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
    public String decodeData(String password) {
        byte[] decodedBytes = Base64.getDecoder().decode(password);
        return new String(decodedBytes);
    }
}
