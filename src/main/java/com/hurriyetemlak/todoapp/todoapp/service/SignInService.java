package com.hurriyetemlak.todoapp.todoapp.service;

import com.hurriyetemlak.todoapp.todoapp.converter.SignInConverter;
import com.hurriyetemlak.todoapp.todoapp.domain.SignIn;
import com.hurriyetemlak.todoapp.todoapp.domain.User;
import com.hurriyetemlak.todoapp.todoapp.model.SignInRequestModel;
import com.hurriyetemlak.todoapp.todoapp.model.SignInResponseModel;
import com.hurriyetemlak.todoapp.todoapp.repository.SignInRepository;
import org.springframework.stereotype.Service;

@Service
public class SignInService {

    private SignInConverter signInConverter;
    private SignInRepository signInRepository;

    public SignInService(SignInConverter signInConverter, SignInRepository signInRepository)
    {
        this.signInConverter = signInConverter;
        this.signInRepository = signInRepository;
    }

    public SignInResponseModel signIn(SignInRequestModel signInRequestModel)
    {
        User user = signInRepository.get(signInRequestModel);
        return signInConverter.convert(user);
    }
}
