package com.example.taskmanager.task_manager.services;

import com.example.taskmanager.task_manager.dtos.auht.AuthenticatedUser;
import com.example.taskmanager.task_manager.dtos.role.RoleRequestDto;
import com.example.taskmanager.task_manager.dtos.role.RoleResponseDto;
import com.example.taskmanager.task_manager.dtos.role.RoleSummaryDto;
import com.example.taskmanager.task_manager.dtos.user.UserResponseDto;
import com.example.taskmanager.task_manager.dtos.permission.PermissionDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long expirationTimeInMillis;

    public String generateToken(AuthenticatedUser user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("userId", user.getId());
        claims.put("permissions", user.getRoles().stream()
                .flatMap(role -> role.getPermission().stream()
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