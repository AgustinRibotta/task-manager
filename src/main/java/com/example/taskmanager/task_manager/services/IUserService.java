package com.example.taskmanager.task_manager.services;

import java.util.List;
import java.util.Optional;

import com.example.taskmanager.task_manager.dtos.user.UserRequestDto;
import com.example.taskmanager.task_manager.dtos.user.UserResponseDto;

public interface IUserService {

    List<UserResponseDto> findAll();
    UserResponseDto findById(Long id);
    UserResponseDto create(UserRequestDto user);
    UserResponseDto put (Long id, UserRequestDto user);
    void delete (Long id);
    Optional<UserResponseDto> findByUsername(String username);
}
