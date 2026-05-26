package com.example.taskmanager.task_manager.services;

import java.util.List;

import com.example.taskmanager.task_manager.dtos.project.ProjectResponseDto;
import com.example.taskmanager.task_manager.dtos.task.TaskRequestDto;
import com.example.taskmanager.task_manager.dtos.task.TaskResponseDto;

public interface ITaskService {

    List<TaskResponseDto> getAll ();
    TaskResponseDto getById (Long taskId);
    TaskResponseDto post (TaskRequestDto task);
    TaskResponseDto put (TaskRequestDto task, Long taskId);
    void delete (Long taskId);
    void deleteUserFromTask(Long userId);
    List<TaskResponseDto> findByUsersId(Long id);
}
