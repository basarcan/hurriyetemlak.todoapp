package com.hurriyetemlak.todoapp.todoapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
