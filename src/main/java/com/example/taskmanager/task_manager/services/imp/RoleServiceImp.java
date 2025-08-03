package com.example.taskmanager.task_manager.services.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.taskmanager.task_manager.dtos.RoleDto;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import com.example.taskmanager.task_manager.exceptions.ResourceNotFoundException;
import com.example.taskmanager.task_manager.mappers.IRoleMapper;
import com.example.taskmanager.task_manager.repositories.IRoleRepository;
import com.example.taskmanager.task_manager.services.IRoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImp implements IRoleService {
    
    private final IRoleRepository roleRepository;
    private final IRoleMapper roleMapper;

    @Override
    public List<RoleDto> getAll() {

       List<RoleDto> roleDtos = roleRepository.findAll().stream()
            .map(role -> {
                RoleDto dto = roleMapper.roleEntityToRoleDto(role);
                return dto;
            })
            .collect(Collectors.toList());

        return roleDtos;
    }

    @Override
    public RoleDto getById(Long id) {
        
        RoleEntity roleEntity = roleRepository.findById(id)
            .orElseThrow(ResourceNotFoundException::new);

        return roleMapper.roleEntityToRoleDto(roleEntity);
    }

    @Override
    public RoleDto post(RoleDto roleDto) {
        String upperName = roleDto.getName() != null ? roleDto.getName().toUpperCase() : null;

        if (roleRepository.findByName(roleDto.getName()).isPresent()) {
            throw new RuntimeException("Name '" + roleDto.getName() + "' is already taken");
        }
        roleDto.setName(upperName);
        RoleEntity roleEntity = roleMapper.roleDtoToRoleEntity(roleDto);
        roleEntity = roleRepository.save(roleEntity);

        return roleMapper.roleEntityToRoleDto(roleEntity);
    }

    @Override
    public RoleDto put(Long id, RoleDto roleDto) {

        RoleEntity roleEntity = roleRepository.findById(id)
            .orElseThrow(ResourceNotFoundException::new);

        roleEntity.setName(roleDto.getName().toUpperCase());
        roleRepository.save(roleEntity);

        return roleMapper.roleEntityToRoleDto(roleEntity);
    }

    @Override
    public void delete(Long id) {
        roleRepository.findById(id)
            .orElseThrow(ResourceNotFoundException::new);
        roleRepository.deleteById(id);
    }


}
