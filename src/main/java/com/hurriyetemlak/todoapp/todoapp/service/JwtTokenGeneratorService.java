package com.hurriyetemlak.todoapp.todoapp.service;

import com.hurriyetemlak.todoapp.todoapp.configuration.JwtConfiguration;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JwtTokenGeneratorService {

    private final JwtConfiguration jwtConfiguration;

    public JwtTokenGeneratorService(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }

    public String generateToken(Map<String, Object> claims, DateTime expirationDate) {
        return Jwts.builder()
                .addClaims(claims)
                .setExpiration(expirationDate.toDate())
                .setIssuedAt(LocalDate.now().toDate())
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SignatureAlgorithm.HS512, jwtConfiguration.getSecretKeySpec()).compact();    }
}
