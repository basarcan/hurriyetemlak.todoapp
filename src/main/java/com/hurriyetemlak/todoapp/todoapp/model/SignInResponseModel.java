package com.hurriyetemlak.todoapp.todoapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponseModel {
    private String token;
    private String email;
}
