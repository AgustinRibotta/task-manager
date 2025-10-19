package com.example.taskmanager.task_manager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.taskmanager.task_manager.dtos.ProjectSummaryDto;
import com.example.taskmanager.task_manager.entities.ProjectEntity;

@Mapper(componentModel = "spring")
public interface IProjectForUserMapper {
    ProjectSummaryDto projectEntityToProjectDtoForUserDto(ProjectEntity entity);
    
    @Mapping(target = "taskEntities", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "users", ignore = true)
    ProjectEntity projectDtoForUserDtoToProjectEntity(ProjectSummaryDto dto);
}