package com.example.taskmanager.task_manager.services.imp;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.taskmanager.task_manager.dtos.TaskDto;
import com.example.taskmanager.task_manager.entities.TaskEntity;
import com.example.taskmanager.task_manager.entities.UserEntity;
import com.example.taskmanager.task_manager.exceptions.ResourceAlreadyExistsException;
import com.example.taskmanager.task_manager.exceptions.ResourceNotFoundException;
import com.example.taskmanager.task_manager.mappers.ITaskMapper;
import com.example.taskmanager.task_manager.repositories.ITaskRepository;
import com.example.taskmanager.task_manager.repositories.IUserRepository;
import com.example.taskmanager.task_manager.services.ITaskService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImp implements ITaskService {

    private final ITaskRepository taskRepository;
    private final ITaskMapper taskMapper;
    private final IUserRepository userRepository;

    @Override
    public List<TaskDto> getAll() {
        
        List<TaskDto>  listTaskDtos = this.taskRepository.findAll().stream()
            .map(task -> {
                TaskDto dto = this.taskMapper.tasktEntityTopTaskDto(task);
                return dto;
            })
            .collect(Collectors.toList());

        return listTaskDtos;
    }

    @Override
    public TaskDto getById(Long taskId) {
        
        TaskEntity taskEntity = this.taskRepository.findById(taskId)
            .orElseThrow(() -> new ResourceNotFoundException(taskId)
            );

        return this.taskMapper.tasktEntityTopTaskDto(taskEntity);
    }

    @Override
    public TaskDto post(TaskDto taskDto) {
        
        if (this.taskRepository.existsByNameAndProjectEntity_Id(taskDto.getName(), taskDto.getProjecDto().getId())) {
            throw new ResourceAlreadyExistsException(taskDto.getName());
        }

        TaskEntity taskEntity = this.taskMapper.tasktDtoToTaskEntity(taskDto);

        Set<UserEntity> userEntities = taskDto.getUserSummaryDto().stream()
            .map( userDto -> this.userRepository.findById(userDto.getId())
                .orElseThrow(() -> new ResourceAlreadyExistsException(userDto.getId()))
            ).collect(Collectors.toSet());
        
        taskEntity.setUsers(userEntities);

        taskEntity = this.taskRepository.save(taskEntity);
        
        return this.taskMapper.tasktEntityTopTaskDto(taskEntity);
    }
    
    @Override
    public TaskDto put(TaskDto taskDto, Long taskId) {

        this.taskRepository.findByName(taskDto.getName())
            .filter(existing -> existing.getId() != taskId)
            .ifPresent(existing -> {
                throw new ResourceAlreadyExistsException(taskDto.getName());
            });

        TaskEntity taskEntity = this.taskRepository.findById(taskId)
            .orElseThrow(() -> new ResourceNotFoundException(taskId));

        taskEntity.setName(taskDto.getName());
        taskEntity.setDescription(taskDto.getDescription());
        taskDto.setProjecDto(taskDto.getProjecDto());
        taskDto.setStatus(taskDto.getStatus());

        Set<UserEntity> userEntities = taskDto.getUserSummaryDto().stream()
            .map(userDto -> this.userRepository.findById(userDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(userDto.getId()))
            ).collect(Collectors.toSet());

        taskEntity.setUsers(userEntities);
        
        taskEntity = this.taskRepository.save(taskEntity);

        return this.taskMapper.tasktEntityTopTaskDto(taskEntity);
    }
    
    @Override
    public void delete(Long taskId) {
        
        TaskEntity taskEntity = this.taskRepository.findById(taskId)
            .orElseThrow(() -> new ResourceNotFoundException(taskId)
            );
        
            this.taskRepository.delete(taskEntity);
    }


}
