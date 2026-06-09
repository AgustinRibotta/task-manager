package com.example.taskmanager.task_manager.mappers.project;

import com.example.taskmanager.task_manager.dtos.project.ProjectRequestDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectResponseDto;
import com.example.taskmanager.task_manager.mappers.task.ITaskSummaryMapper;
import com.example.taskmanager.task_manager.mappers.user.IUserSummaryMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.taskmanager.task_manager.entities.ProjectEntity;

@Mapper(componentModel = "spring", uses = {ITaskSummaryMapper.class, IUserSummaryMapper.class})
public interface IProjectMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "owner", ignore = true)
    ProjectEntity requestToEntity(ProjectRequestDto dto);

    @Mapping(source = "tasks" , target = "tasks")
    @Mapping(source = "users" , target = "users")
    @Mapping(source = "owner", target = "owner")
    ProjectResponseDto entityToResponse(ProjectEntity entity);
}