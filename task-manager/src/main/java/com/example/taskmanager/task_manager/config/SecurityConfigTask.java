package com.example.taskmanager.task_manager.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.taskmanager.task_manager.repositories.IUserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SecurityConfigTask {

    private final IUserRepository userRepository;

    @Transactional
    public boolean isTask(Long projectId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        String username = authentication.getName();

        return userRepository.findByUsernameWithTask(username)
            .map(user -> user.getProjectEntities().stream()
                    .anyMatch(project -> project.getId() == projectId))
            .orElse(false);
    }
}
