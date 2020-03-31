package com.hurriyetemlak.todoapp.todoapp.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponse {
    private String token;
    private String firstName;
    private String lastName;
}
