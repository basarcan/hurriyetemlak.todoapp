package com.hurriyetemlak.todoapp.todoapp.service;
import com.hurriyetemlak.todoapp.todoapp.converter.SignUpConverter;
import com.hurriyetemlak.todoapp.todoapp.domain.User;
import com.hurriyetemlak.todoapp.todoapp.model.SignUpRequestModel;
import com.hurriyetemlak.todoapp.todoapp.repository.SignUpRepository;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    private final SignUpConverter signUpConverter;
    private final SignUpRepository signUpRepository;

    public SignUpService(SignUpConverter signUpConverter, SignUpRepository signUpRepository) {
        this.signUpConverter = signUpConverter;
        this.signUpRepository = signUpRepository;
    }

    public void signUp(SignUpRequestModel signUpRequestModel) {
        User user = signUpConverter.convert(signUpRequestModel);
        signUpRepository.save(user);
    }
}
