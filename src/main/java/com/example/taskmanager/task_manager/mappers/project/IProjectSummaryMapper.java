package com.example.taskmanager.task_manager.mappers.project;

import com.example.taskmanager.task_manager.dtos.project.ProjectSummaryDto;
import com.example.taskmanager.task_manager.entities.ProjectEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IProjectSummaryMapper {
    ProjectSummaryDto toDto (ProjectEntity entity);
}
