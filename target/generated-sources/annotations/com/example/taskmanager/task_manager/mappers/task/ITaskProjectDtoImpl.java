package com.example.taskmanager.task_manager.mappers.task;

import com.example.taskmanager.task_manager.dtos.task.TaskProjectDto;
import com.example.taskmanager.task_manager.entities.ProjectEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-28T21:16:03+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Arch Linux)"
)
@Component
public class ITaskProjectDtoImpl implements ITaskProjectDto {

    @Override
    public TaskProjectDto toTaskProjectDto(ProjectEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TaskProjectDto taskProjectDto = new TaskProjectDto();

        taskProjectDto.setId( entity.getId() );
        taskProjectDto.setName( entity.getName() );

        return taskProjectDto;
    }
}
