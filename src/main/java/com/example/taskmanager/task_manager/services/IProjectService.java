package com.example.taskmanager.task_manager.services;

import java.util.List;

import com.example.taskmanager.task_manager.dtos.ProjectDto;

public interface IProjectService {

    List<ProjectDto> getAll();
    ProjectDto getById (Long id);
    ProjectDto post (ProjectDto projectDto);
    ProjectDto put (ProjectDto projectDto, Long id);
    void delete (Long id);
    void deleteUserFromProjects(Long userId);
    List<ProjectDto> findByUsersId (Long userId);
}
