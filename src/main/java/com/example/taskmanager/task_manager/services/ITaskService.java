package com.example.taskmanager.task_manager.services;

import java.util.List;

import com.example.taskmanager.task_manager.dtos.task.TaskRequestDto;
import com.example.taskmanager.task_manager.dtos.task.TaskResponseDto;

public interface ITaskService {

    List<TaskResponseDto> findAll();
    TaskResponseDto findById(Long taskId);
    TaskResponseDto create(TaskRequestDto task);
    TaskResponseDto postTaskProject (TaskRequestDto task, Long id);
    TaskResponseDto update(TaskRequestDto task, Long taskId);
    void delete (Long taskId);
    void deleteUserFromTask(Long userId);
    List<TaskResponseDto> findByUsersId(Long id);
}
