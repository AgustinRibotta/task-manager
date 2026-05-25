package com.example.taskmanager.task_manager.mappers.task;

import com.example.taskmanager.task_manager.dtos.task.TaskRequestDto;
import com.example.taskmanager.task_manager.dtos.task.TaskResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.taskmanager.task_manager.entities.TaskEntity;

@Mapper(componentModel = "spring")
public interface ITaskMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    TaskEntity tasktDtoToTaskEntity (TaskRequestDto dto);

    TaskResponseDto taskEntityTopTaskDto(TaskEntity entity);

}
