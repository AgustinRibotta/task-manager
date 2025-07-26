package com.example.taskmanager.task_manager.services.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.taskmanager.task_manager.dtos.UserDto;
import com.example.taskmanager.task_manager.mappers.IUserMapper;
import com.example.taskmanager.task_manager.repositories.IUserRepository;
import com.example.taskmanager.task_manager.services.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements IUserService {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
        .map(user -> {
            UserDto dto = userMapper.userToUserDto(user);
            dto.setPassword(null);
            return dto;
        })
        .collect(Collectors.toList());

    }


    @Override
    public UserDto getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public UserDto post(UserDto user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'post'");
    }

    @Override
    public UserDto put(Long id, UserDto user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'put'");
    }

    @Override
    public Boolean delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }


}
