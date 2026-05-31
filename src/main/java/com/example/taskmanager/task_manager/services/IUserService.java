package com.example.taskmanager.task_manager.services;

import java.util.List;
import java.util.Optional;

import com.example.taskmanager.task_manager.dtos.user.UserDto;

public interface IUserService {

    List<UserDto> getAll();
    UserDto getById (Long id);
    UserDto post (UserDto user);
    UserDto put (Long id, UserDto user);
    void delete (Long id);
    Optional<UserDto> findByUsername(String username);
}
