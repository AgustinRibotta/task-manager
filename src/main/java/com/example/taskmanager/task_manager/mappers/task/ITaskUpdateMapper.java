package com.example.taskmanager.task_manager.mappers.task;

import com.example.taskmanager.task_manager.dtos.task.TaskUpdateRequest;
import com.example.taskmanager.task_manager.entities.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface ITaskUpdateMapper {

    TaskEntity toEntity (TaskUpdateRequest dto);

}
