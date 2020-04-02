package com.hurriyetemlak.todoapp.todoapp.service;

import com.hurriyetemlak.todoapp.todoapp.domain.User;
import com.hurriyetemlak.todoapp.todoapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class VerifyService {
    private final UserRepository userRepository;

    public VerifyService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String verify(String token) {
        User user = userRepository.verifyToken(token);
        if(user != null) {
            return user.getId();
        }
        return "";
    }
}
