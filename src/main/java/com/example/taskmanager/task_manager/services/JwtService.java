package com.example.taskmanager.task_manager.services;

import com.example.taskmanager.task_manager.dtos.RoleDto;
import com.example.taskmanager.task_manager.dtos.UserDto;
import com.example.taskmanager.task_manager.dtos.project.PermissionDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long expirationTimeInMillis;

    public String generateToken(UserDto userDetails) {
        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("userId", userDetails.getId());
        claims.put("roles", userDetails.getRoles().stream()
                                    .map(RoleDto::getName)
                                    .collect(Collectors.toList()));
        claims.put("permissions", userDetails.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream()
                        .map(PermissionDto::getName))
                        .distinct()
                        .collect(Collectors.toList())
        );

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMillis))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }
}