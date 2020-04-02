package com.hurriyetemlak.todoapp.todoapp.controller;

import com.hurriyetemlak.todoapp.todoapp.service.VerifyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("verify")
public class VerifyController {
    private final VerifyService verifyService;

    public VerifyController(VerifyService verifyService) {
        this.verifyService = verifyService;
    }

    @GetMapping
    public String signUp(@RequestParam String token) {
        return verifyService.verify(token);
    }
}
