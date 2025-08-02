package com.example.taskmanager.task_manager.services.imp;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.taskmanager.task_manager.dtos.UserDto;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import com.example.taskmanager.task_manager.entities.UserEntity;
import com.example.taskmanager.task_manager.exceptions.ResourceNotFoundException;
import com.example.taskmanager.task_manager.mappers.IUserMapper;
import com.example.taskmanager.task_manager.repositories.IRoleRepository;
import com.example.taskmanager.task_manager.repositories.IUserRepository;
import com.example.taskmanager.task_manager.services.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements IUserService {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    private final PasswordEncoder passwordEncoder; 
    private final IRoleRepository roleRepository;

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
        UserEntity user = userRepository.findById(id)
            .orElseThrow(ResourceNotFoundException::new);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto post(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new RuntimeException("Username '" + userDto.getUsername() + "' is already taken");
        }

        UserEntity user = userMapper.userDtoToUser(userDto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<RoleEntity> roles = userDto.getRoleEntities().stream()
            .map( roleDto -> roleRepository.findById(roleDto.getId())
                .orElseThrow(ResourceNotFoundException::new))
            .collect(Collectors.toSet());

        user.setRoleEntities(roles);

        user = userRepository.save(user);

        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto put(Long id, UserDto userDto) {

        UserEntity user = userRepository.findById(id)
            .orElseThrow(ResourceNotFoundException::new);
        
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode((userDto.getPassword())));
        
        Set<RoleEntity> roles = userDto.getRoleEntities().stream()
            .map( roleDto -> roleRepository.findById(roleDto.getId())
                .orElseThrow(ResourceNotFoundException::new))
            .collect(Collectors.toSet());
        
        user.setRoleEntities(roles);

        user = userRepository.save(user);

        return userMapper.userToUserDto(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }


}
