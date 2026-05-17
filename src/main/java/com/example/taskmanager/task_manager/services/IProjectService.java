package com.example.taskmanager.task_manager.services;

import java.util.List;

import com.example.taskmanager.task_manager.dtos.ProjectDto;

public interface IProjectService {

    List<ProjectDto> getAll();
    ProjectDto getById (Long id);
    ProjectDto post (ProjectDto projecDto);
    ProjectDto put (ProjectDto projecDto, Long id);
    void delete (Long id);
    void deleteUserFromProjects(Long userId);
    
}
