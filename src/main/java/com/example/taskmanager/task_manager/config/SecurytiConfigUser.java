package com.example.taskmanager.task_manager.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.taskmanager.task_manager.entities.UserEntity;
import com.example.taskmanager.task_manager.repositories.IUserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SecurytiConfigUser {

    final IUserRepository userRepository;

    @Transactional
    public boolean isUser(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        String username = authentication.getName();  

        UserEntity user = userRepository.findById(id).orElse(null);
        if (user == null) return false;

    return user.getUsername().equals(username);
}

}
