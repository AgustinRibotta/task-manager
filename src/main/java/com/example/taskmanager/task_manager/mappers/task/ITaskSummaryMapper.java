package com.example.taskmanager.task_manager.mappers.task;

import com.example.taskmanager.task_manager.dtos.task.TaskSummaryDto;
import com.example.taskmanager.task_manager.entities.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITaskSummaryMapper {
    TaskSummaryDto toDto (TaskEntity entity);
}
