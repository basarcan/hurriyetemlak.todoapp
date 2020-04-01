package com.hurriyetemlak.todoapp.todoapp.controller;

import com.hurriyetemlak.todoapp.todoapp.exception.exceptions.PasswordDoesNotExistException;
import com.hurriyetemlak.todoapp.todoapp.model.request.SignInRequest;
import com.hurriyetemlak.todoapp.todoapp.model.response.SignInResponse;
import com.hurriyetemlak.todoapp.todoapp.service.SignInService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sign-in")
public class SignInController {

    private final SignInService signInService;

    public SignInController(SignInService signInService){
        this.signInService = signInService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public SignInResponse signIn(@RequestBody  SignInRequest signInRequest) throws PasswordDoesNotExistException {
        return signInService.signIn(signInRequest);
    }


}
