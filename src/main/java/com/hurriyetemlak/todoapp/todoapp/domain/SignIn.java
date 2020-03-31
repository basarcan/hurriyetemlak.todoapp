package com.hurriyetemlak.todoapp.todoapp.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/*
  Id veri tabanından gelecek olan id
  giriş yaparken kullanacağı email aderesi
  giriş yaparken kullanacağı password
* */
public class SignIn {
    private Long Id;
    private String email;
    private String password;
}
