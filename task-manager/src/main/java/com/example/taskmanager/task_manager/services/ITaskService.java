package com.example.taskmanager.task_manager.services;

import java.util.List;

import com.example.taskmanager.task_manager.dtos.TaskDto;

public interface ITaskService {

    List<TaskDto> getAll ();
    TaskDto getById (Long taskId);
    TaskDto post (TaskDto taskDto);
    TaskDto put (TaskDto taskDto, Long taskId);
    void delete (Long taskId);

}
