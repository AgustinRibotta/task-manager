package com.example.taskmanager.task_manager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.taskmanager.task_manager.dtos.TaskDto;
import com.example.taskmanager.task_manager.entities.TaskEntity;

@Mapper(componentModel = "spring")
public interface ITaskMapper {  

    @Mapping(source = "projectEntity", target = "projectSummaryDto")
    @Mapping(source = "users", target = "userSummaryDto")
    TaskDto tasktEntityTopTaskDto (TaskEntity tasktEntity);

    @Mapping(source = "projectSummaryDto", target = "projectEntity")
    @Mapping(source = "userSummaryDto", target = "users")
    TaskEntity tasktDtoToTaskEntity (TaskDto taskDto);
}
