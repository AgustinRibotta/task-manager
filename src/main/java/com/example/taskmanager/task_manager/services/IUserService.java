package com.example.taskmanager.task_manager.services;

import java.util.List;
import java.util.Optional;

import com.example.taskmanager.task_manager.dtos.auht.AuthenticatedUser;
import com.example.taskmanager.task_manager.dtos.user.UserRequestDto;
import com.example.taskmanager.task_manager.dtos.user.UserResponseDto;
import com.example.taskmanager.task_manager.entities.UserEntity;

public interface IUserService {

    List<UserResponseDto> findAll();
    UserResponseDto findById(Long id);
    UserResponseDto create(UserRequestDto user);
    UserResponseDto update(Long id, UserRequestDto user);
    void delete (Long id);
    Optional<AuthenticatedUser> findByUsername(String username);
}
