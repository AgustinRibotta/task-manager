package com.example.taskmanager.task_manager.services.imp;

import java.util.List;
import java.util.stream.Collectors;

import com.example.taskmanager.task_manager.dtos.role.RoleRequestDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.taskmanager.task_manager.entities.RoleEntity;
import com.example.taskmanager.task_manager.exceptions.ResourceAlreadyExistsException;
import com.example.taskmanager.task_manager.exceptions.ResourceNotFoundException;
import com.example.taskmanager.task_manager.mappers.role.IRoleMapper;
import com.example.taskmanager.task_manager.repositories.IRoleRepository;
import com.example.taskmanager.task_manager.services.IRoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImp implements IRoleService {
    
    private final IRoleRepository roleRepository;
    private final IRoleMapper roleMapper;
    @Override

    public List<RoleRequestDto> getAll() {

       List<RoleRequestDto> dtos = this.roleRepository.findAll(Sort.by("id")).stream()
            .map(role -> {
                RoleRequestDto dto = this.roleMapper.roleEntityToRoleDto(role);
                return dto;
            })
            .collect(Collectors.toList());

        return dtos;
    }

    @Override
    public RoleRequestDto getById(Long id) {
        
        RoleEntity roleEntity = this.roleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id)
            );

        return this.roleMapper.roleEntityToRoleDto(roleEntity);
    }

    @Override
    public RoleRequestDto post(RoleRequestDto roleRequestDto) {
        String upperName = roleRequestDto.getName() != null ? roleRequestDto.getName().toUpperCase() : null;

        if (this.roleRepository.findByName(roleRequestDto.getName()).isPresent()) {
            throw new ResourceAlreadyExistsException(roleRequestDto.getName());
        }
        roleRequestDto.setName(upperName);
        RoleEntity roleEntity = this.roleMapper.roleDtoToRoleEntity(roleRequestDto);
        roleEntity = this.roleRepository.save(roleEntity);

        return this.roleMapper.roleEntityToRoleDto(roleEntity);
    }

    @Override
    public RoleRequestDto put(Long id, RoleRequestDto roleRequestDto) {

        RoleEntity roleEntity = this.roleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id));

        roleEntity.setName(roleRequestDto.getName().toUpperCase());
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
