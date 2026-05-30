package com.example.taskmanager.task_manager.services;

import java.util.List;

import com.example.taskmanager.task_manager.dtos.project.ProjectRequestDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectResponseDto;

public interface IProjectService {

    List<ProjectResponseDto> getAll();
    ProjectResponseDto getById (Long id);
    ProjectResponseDto post (ProjectRequestDto projectDto);
    ProjectResponseDto put (ProjectRequestDto projectDto, Long id);
    void delete (Long id);
    void deleteUserFromProjects(Long userId);
    List<ProjectResponseDto> findByUsersId (Long userId);
    ProjectResponseDto putOwnerProject (Long projectId, Long ownerId);
}
