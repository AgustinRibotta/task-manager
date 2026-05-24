package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.ProjectTaskDto;
import com.example.taskmanager.task_manager.entities.TaskEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-24T23:06:43+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Ubuntu)"
)
@Component
public class IProjectTaskMapperImpl implements IProjectTaskMapper {

    @Override
    public ProjectTaskDto toProjectTaskDto(TaskEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectTaskDto projectTaskDto = new ProjectTaskDto();

        projectTaskDto.setId( entity.getId() );
        projectTaskDto.setName( entity.getName() );
        projectTaskDto.setDescription( entity.getDescription() );
        projectTaskDto.setStatus( entity.getStatus() );

        return projectTaskDto;
    }
}
