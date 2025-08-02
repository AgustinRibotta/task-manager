package com.example.taskmanager.task_manager.services.imp;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.taskmanager.task_manager.entities.RoleEntity;
import com.example.taskmanager.task_manager.entities.UserEntity;
import com.example.taskmanager.task_manager.repositories.IUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private IUserRepository userRepository;

    public UserDetailsServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String[] roles = user.getRoleEntities().stream()
            .map(RoleEntity::getName)
            .toArray(String[]::new);

        return org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            .roles(roles)
            .build();
    }
}