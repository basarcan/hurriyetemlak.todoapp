package com.hurriyetemlak.todoapp.todoapp.converter;

import com.hurriyetemlak.todoapp.todoapp.domain.SignIn;
import com.hurriyetemlak.todoapp.todoapp.domain.User;
import com.hurriyetemlak.todoapp.todoapp.model.SignInRequestModel;
import com.hurriyetemlak.todoapp.todoapp.model.SignInResponseModel;
import com.hurriyetemlak.todoapp.todoapp.service.EncryptionService;
import org.springframework.stereotype.Component;

@Component
public class SignInConverter {
    private final EncryptionService encryptionService;

    public SignInConverter(EncryptionService encryptionService) {this.encryptionService = encryptionService;}

    public SignInResponseModel convert(User user)
    {
        SignInResponseModel signInResponseModel = new SignInResponseModel();

        signInResponseModel.setEmail(user.getEmail());
        return signInResponseModel;
    }
}
