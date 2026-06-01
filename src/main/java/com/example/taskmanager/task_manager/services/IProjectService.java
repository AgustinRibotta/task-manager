package com.example.taskmanager.task_manager.services;

import java.util.List;

import com.example.taskmanager.task_manager.dtos.project.ProjectRequestDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectResponseDto;
import com.example.taskmanager.task_manager.dtos.user.AssignUsersRequest;

public interface IProjectService {

    List<ProjectResponseDto> findAll();
    ProjectResponseDto findById(Long id);
    List<ProjectResponseDto> findByUsersId (Long userId);
    ProjectResponseDto create(ProjectRequestDto projectDto);
    ProjectResponseDto update(ProjectRequestDto projectDto, Long id);
    void delete (Long id);
    void removeUserFromAllProject(Long userId);
    void changeOwner(Long projectId, Long ownerId);
    void assignUsersToProject(Long projectId, AssignUsersRequest userIds);
    void removeUserFromAllProject(Long projectId, Long userId);
}
