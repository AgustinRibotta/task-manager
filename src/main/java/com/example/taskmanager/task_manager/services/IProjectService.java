package com.example.taskmanager.task_manager.services;

import com.example.taskmanager.task_manager.dtos.project.ProjectRequestDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectResponseDto;
import com.example.taskmanager.task_manager.dtos.user.UsersAssignRequestDto;

import java.util.List;

public interface IProjectService {

    List<ProjectResponseDto> findAll();
    ProjectResponseDto findById(Long id);
    List<ProjectResponseDto> findByUsersId(Long userId);
    ProjectResponseDto create(ProjectRequestDto projectDto);
    ProjectResponseDto update(ProjectRequestDto projectDto, Long id);
    void delete(Long id);
    void removeUserFromAllProject(Long userId);
    void changeOwner(Long projectId, Long ownerId);
    // USER
    void assignUsersToProject(Long projectId, UsersAssignRequestDto userIds);
    void removeUserFromAllProject(Long projectId, Long userId);
}
