package com.example.taskmanager.task_manager.mappers.task;

import com.example.taskmanager.task_manager.dtos.task.TaskProjectDto;
import com.example.taskmanager.task_manager.entities.ProjectEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITaskProjectDto {
    TaskProjectDto toTaskProjectDto (ProjectEntity entity);
}
