package com.hurriyetemlak.todoapp.todoapp.controller;

import com.hurriyetemlak.todoapp.todoapp.model.request.SignUpRequestModel;
import com.hurriyetemlak.todoapp.todoapp.service.SignUpService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sign-up")
public class SignUpController {

    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody SignUpRequestModel signUpRequestModel){
        signUpService.signUp(signUpRequestModel);
    }

}
