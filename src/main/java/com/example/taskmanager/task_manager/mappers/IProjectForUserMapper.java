package com.example.taskmanager.task_manager.mappers;

import org.mapstruct.Mapper;

import com.example.taskmanager.task_manager.dtos.ProjectSummaryDto;
import com.example.taskmanager.task_manager.entities.ProjectEntity;

@Mapper(componentModel = "spring")
public interface IProjectForUserMapper {
    ProjectSummaryDto projectEntityToProjectDtoForUserDto(ProjectEntity entity);
    ProjectEntity projectDtoForUserDtoToProjectEntity(ProjectSummaryDto dto);
}