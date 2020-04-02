package com.hurriyetemlak.todoapp.todoapp.converter;

import com.hurriyetemlak.todoapp.todoapp.domain.User;
import com.hurriyetemlak.todoapp.todoapp.model.SignUpRequestModel;
import com.hurriyetemlak.todoapp.todoapp.service.EncryptionService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component//Converter tag i yok bu benim projemin bir componentidir gel buraya bak
public class SignUpConverter {

    private final EncryptionService encryptionService;

    public SignUpConverter(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    public User convert(SignUpRequestModel signUpRequestModel){
        User user = new User();
        user.setFirstName(signUpRequestModel.getFirstName());
        user.setLastName(signUpRequestModel.getLastName());
        user.setEmail(signUpRequestModel.getEmail());
        user.setPassword(encryptionService.encodeData(signUpRequestModel.getPassword()));
        user.setCreatedDate(new Date());
        return user;
    }

}
