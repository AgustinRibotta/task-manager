package com.example.taskmanager.task_manager.services.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.taskmanager.task_manager.dtos.user.UserRequestDto;
import com.example.taskmanager.task_manager.dtos.user.UserResponseDto;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.taskmanager.task_manager.entities.UserEntity;
import com.example.taskmanager.task_manager.exceptions.ResourceAlreadyExistsException;
import com.example.taskmanager.task_manager.exceptions.ResourceNotFoundException;
import com.example.taskmanager.task_manager.mappers.user.IUserMapper;
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
    public List<UserResponseDto> findAll() {
        return this.userRepository.findAll(Sort.by("id")).stream()
        .map(user -> {
            UserResponseDto dto = this.userMapper.toDto(user);
            dto.setPassword(null);
            return dto;
        })
        .collect(Collectors.toList());

    }

    @Override
    public UserResponseDto findById(Long id) {
        
        UserEntity user = this.userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id));
        return this.userMapper.toDto(user);
    }

    @Override
    public UserResponseDto create(UserRequestDto request) {
        if (this.userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ResourceAlreadyExistsException(request.getUsername());
        }

        UserEntity user = this.userMapper.toEntity(request);

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user = this.userRepository.save(user);

        return this.userMapper.toDto(user);
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto userResponseDto) {

        UserEntity user = this.userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id));
        
        user.setUsername(userResponseDto.getUsername());
        user.setEmail(userResponseDto.getEmail());
        user.setPassword(passwordEncoder.encode((userResponseDto.getPassword())));
        
        user = this.userRepository.save(user);

        return this.userMapper.toDto(user);
    }

    @Override
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

}
