package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.TaskSummaryDto;
import com.example.taskmanager.task_manager.entities.TaskEntity;

public interface ITaskForProjectMapper {
    TaskSummaryDto tasktEntityTopTaskDto (TaskEntity tasktEntity);

    TaskEntity tasktDtoToTaskEntity (TaskSummaryDto taskDto);
}
