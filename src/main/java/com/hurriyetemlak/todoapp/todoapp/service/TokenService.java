package com.hurriyetemlak.todoapp.todoapp.service;

import com.hurriyetemlak.todoapp.todoapp.domain.User;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {
    private final JwtTokenGeneratorService jwtTokenGeneratorService;

    public TokenService(JwtTokenGeneratorService jwtTokenGeneratorService) {
        this.jwtTokenGeneratorService = jwtTokenGeneratorService;
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        DateTime expirationDate = DateTime.now().plusDays(1);
        return jwtTokenGeneratorService.generateToken(claims,expirationDate).compact();

    }
}
