package com.example.taskmanager.task_manager.services;

import java.util.List;

import com.example.taskmanager.task_manager.dtos.task.TaskRequestDto;
import com.example.taskmanager.task_manager.dtos.task.TaskResponseDto;
import com.example.taskmanager.task_manager.dtos.task.TaskUpdateRequest;
import com.example.taskmanager.task_manager.dtos.user.UsersAssignRequestDto;

public interface ITaskService {

    List<TaskResponseDto> findAll();
    TaskResponseDto findById(Long taskId);
    TaskResponseDto create(TaskRequestDto task);
    TaskResponseDto postTaskProject (TaskRequestDto task, Long id);
    TaskResponseDto update(TaskUpdateRequest task, Long taskId);
    List<TaskResponseDto> findByUsersId(Long id);
    void delete (Long taskId);
    void deleteUserFromTask(Long userId);

    void assignUsersToProject(Long taskId, UsersAssignRequestDto request);
    void removeUsersFromProject(Long taskId, Long userId);
}
