package com.example.taskmanager.task_manager.mappers.task;

import com.example.taskmanager.task_manager.dtos.task.TaskUserDto;
import com.example.taskmanager.task_manager.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITaskUserDto {
    TaskUserDto toToTaskUserDto (UserEntity entity);
}
