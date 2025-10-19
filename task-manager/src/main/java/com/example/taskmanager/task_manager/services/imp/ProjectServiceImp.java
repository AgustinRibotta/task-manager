package com.example.taskmanager.task_manager.services.imp;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.taskmanager.task_manager.dtos.ProjecDto;
import com.example.taskmanager.task_manager.entities.ProjectEntity;
import com.example.taskmanager.task_manager.entities.UserEntity;
import com.example.taskmanager.task_manager.exceptions.ResourceAlreadyExistsException;
import com.example.taskmanager.task_manager.exceptions.ResourceNotFoundException;
import com.example.taskmanager.task_manager.mappers.IProjectMapper;
import com.example.taskmanager.task_manager.repositories.IProjectRepository;
import com.example.taskmanager.task_manager.repositories.IUserRepository;
import com.example.taskmanager.task_manager.services.IProjectService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImp implements IProjectService {

    private final IProjectRepository projectRepository;
    private final IProjectMapper projectMapper;
    private final IUserRepository userRepository;

    @Override
    public List<ProjecDto> getAll() {
        
        List<ProjecDto> projecDtos = this.projectRepository.findAll().stream()
        .map(project -> {
            ProjecDto dto = this.projectMapper.projectEntityToProjecDto(project);
            return dto;
        })
        .collect(Collectors.toList());

        return projecDtos;
    }

    @Override
    public ProjecDto getById(Long id) {

        ProjectEntity projectEntity = this.projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id)
            );

        return this.projectMapper.projectEntityToProjecDto(projectEntity);
    }

    @Override
    public ProjecDto post(ProjecDto projecDto) {

        if (this.projectRepository.findByName(projecDto.getName()).isPresent()) {
            throw new ResourceAlreadyExistsException(projecDto.getName());
        }

        ProjectEntity projectEntity = this.projectMapper.projectDtoToProjectEntity(projecDto);
        
        Set<UserEntity> userEntities = projecDto.getUsersDtos().stream()
            .map( userDto -> this.userRepository.findById(userDto.getId())
                .orElseThrow(() -> new ResourceAlreadyExistsException(userDto.getId()))
            ).collect(Collectors.toSet());

        projectEntity.setUsers(userEntities);

        projectEntity = this.projectRepository.save(projectEntity);
        
        return this.projectMapper.projectEntityToProjecDto(projectEntity);
    }

    @Override
    public ProjecDto put(ProjecDto projecDto, Long id) {

        this.projectRepository.findByName(projecDto.getName())
            .filter(existing -> existing.getId() != id)
            .ifPresent(existing -> {
                throw new ResourceAlreadyExistsException(projecDto.getName());
            });

        ProjectEntity projectEntity = this.projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id));

        projectEntity.setName(projecDto.getName());
        projectEntity.setDescription(projecDto.getDescription());

        Set<UserEntity> userEntities = projecDto.getUsersDtos().stream()
            .map(userDto -> this.userRepository.findById(userDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(userDto.getId()))
            ).collect(Collectors.toSet());

        projectEntity.setUsers(userEntities);

        projectEntity = this.projectRepository.save(projectEntity);

        return this.projectMapper.projectEntityToProjecDto(projectEntity);
    }

    @Override
    public void delete(Long id) {
        this.projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id));
            
        this.projectRepository.deleteById(id);
    }

}
