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
    TaskResponseDto update(TaskUpdateRequest task, Long taskId);
    List<TaskResponseDto> findByUsersId(Long id);
    void delete (Long taskId);
    void deleteUserFromTask(Long userId);

    TaskResponseDto postTaskProject (TaskRequestDto task, Long id);

    void assignUsersToTask(Long taskId, UsersAssignRequestDto request);
    void removeUsersFromTask(Long taskId, Long userId);

    void updateStatus(Long taskId, String status);
}
