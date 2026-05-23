package com.example.taskmanager.task_manager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.taskmanager.task_manager.dtos.TaskDto;
import com.example.taskmanager.task_manager.entities.TaskEntity;

@Mapper(componentModel = "spring")
public interface ITaskMapper {  

    @Mapping(source = "projectEntity", target = "projectResponseDto")
    @Mapping(source = "users", target = "userDto")
    TaskDto tasktEntityTopTaskDto (TaskEntity taskEntity);

    @Mapping(source = "projectResponseDto", target = "projectEntity")
    @Mapping(source = "userDto", target = "users")
    TaskEntity tasktDtoToTaskEntity (TaskDto taskDto);
}
