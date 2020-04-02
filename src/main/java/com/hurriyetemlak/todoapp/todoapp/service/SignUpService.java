package com.hurriyetemlak.todoapp.todoapp.service;

import com.hurriyetemlak.todoapp.todoapp.converter.SignUpConverter;
import com.hurriyetemlak.todoapp.todoapp.domain.User;
import com.hurriyetemlak.todoapp.todoapp.model.SignUpRequestModel;
import com.hurriyetemlak.todoapp.todoapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    private final SignUpConverter signUpConverter;
    private final UserRepository userRepository;

    public SignUpService(SignUpConverter signUpConverter, UserRepository userRepository) {
        this.signUpConverter = signUpConverter;
        this.userRepository = userRepository;
    }

    public void signUp(SignUpRequestModel signUpRequestModel) {
        User user = signUpConverter.convert(signUpRequestModel);
        userRepository.save(user);
    }
}
