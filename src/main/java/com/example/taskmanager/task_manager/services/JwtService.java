package com.example.taskmanager.task_manager.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.taskmanager.task_manager.entities.UserEntity;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long expirationTimeInMillis;

    public String generateToken(UserEntity userEntity) {
        Claims claims = Jwts.claims().setSubject(userEntity.getUsername());
        claims.put("userId", userEntity.getId());
        claims.put("roles", userEntity.getAuthorities().stream()
                                    .map(auth -> auth.getAuthority())
                                    .collect(Collectors.toList()));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMillis))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }
}