package com.example.taskmanager.task_manager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.taskmanager.task_manager.dtos.TaskDto;
import com.example.taskmanager.task_manager.entities.TaskEntity;

@Mapper(componentModel = "spring", uses = { IProjectForUserMapper.class })
public interface ITaskMapper {

    @Mapping(source = "projectEntity", target = "projectDto")
    @Mapping(source = "users", target = "userSummaryDto") 
    TaskDto tasktEntityTopTaskDto(TaskEntity taskEntity);

    @Mapping(source = "projectDto", target = "projectEntity")
    @Mapping(source = "userSummaryDto", target = "users")
    TaskEntity tasktDtoToTaskEntity(TaskDto taskDto);
}
