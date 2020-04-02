package com.hurriyetemlak.todoapp.todoapp.service;

import com.hurriyetemlak.todoapp.todoapp.configuration.JwtSecurityTokenConfig;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtTokenGeneratorService {

    private final JwtSecurityTokenConfig jwtSecurityTokenConfig;

    public JwtTokenGeneratorService(JwtSecurityTokenConfig jwtSecurityTokenConfig) {
        this.jwtSecurityTokenConfig = jwtSecurityTokenConfig;
    }

    public JwtBuilder generateToken(Map<String, Object> claims, DateTime expirationDate) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .addClaims(claims)
                .setExpiration(expirationDate.toDate())
                .setIssuedAt(LocalDate.now().toDate())
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SignatureAlgorithm.HS512, jwtSecurityTokenConfig.getSecretKeySpec());
        return jwtBuilder;
    }
}
