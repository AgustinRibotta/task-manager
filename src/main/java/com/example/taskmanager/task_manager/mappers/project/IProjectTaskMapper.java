package com.example.taskmanager.task_manager.mappers.project;

import com.example.taskmanager.task_manager.dtos.project.ProjectTaskDto;
import com.example.taskmanager.task_manager.entities.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IProjectTaskMapper {
    ProjectTaskDto toProjectTaskDto(TaskEntity entity);
}
