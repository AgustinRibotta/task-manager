package com.example.taskmanager.task_manager.mappers;

import org.mapstruct.Mapper;

import com.example.taskmanager.task_manager.dtos.TaskDto;
import com.example.taskmanager.task_manager.entities.TaskEntity;

@Mapper(componentModel = "spring", uses = { IProjectMapper.class })
public interface ITaskMapper {  

    TaskDto tasktEntityTopTaskDto (TaskEntity tasktEntity);

    TaskEntity tasktDtoToTaskEntity (TaskDto taskDto);
}
