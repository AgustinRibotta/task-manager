package com.example.taskmanager.task_manager.services.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.taskmanager.task_manager.dtos.RoleDto;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import com.example.taskmanager.task_manager.exceptions.ResourceAlreadyExistsException;
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

       List<RoleDto> roleDtos = this.roleRepository.findAll().stream()
            .map(role -> {
                RoleDto dto = this.roleMapper.roleEntityToRoleDto(role);
                return dto;
            })
            .collect(Collectors.toList());

        return roleDtos;
    }

    @Override
    public RoleDto getById(Long id) {
        
        RoleEntity roleEntity = this.roleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id)
            );

        return this.roleMapper.roleEntityToRoleDto(roleEntity);
    }

    @Override
    public RoleDto post(RoleDto roleDto) {
        String upperName = roleDto.getName() != null ? roleDto.getName().toUpperCase() : null;

        if (this.roleRepository.findByName(roleDto.getName()).isPresent()) {
            throw new ResourceAlreadyExistsException(roleDto.getName());
        }
        roleDto.setName(upperName);
        RoleEntity roleEntity = this.roleMapper.roleDtoToRoleEntity(roleDto);
        roleEntity = this.roleRepository.save(roleEntity);

        return this.roleMapper.roleEntityToRoleDto(roleEntity);
    }

    @Override
    public RoleDto put(Long id, RoleDto roleDto) {

        RoleEntity roleEntity = this.roleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id));

        roleEntity.setName(roleDto.getName().toUpperCase());
        this.roleRepository.save(roleEntity);

        return this.roleMapper.roleEntityToRoleDto(roleEntity);
    }

    @Override
    public void delete(Long id) {
        this.roleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id));
        this.roleRepository.deleteById(id);
    }


}
