package com.example.taskmanager.task_manager.mappers.task;

import com.example.taskmanager.task_manager.dtos.task.TaskUpdateRequest;
import com.example.taskmanager.task_manager.entities.TaskEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-13T22:40:51+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Arch Linux)"
)
@Component
public class ITaskUpdateMapperImpl implements ITaskUpdateMapper {

    @Override
    public TaskEntity toEntity(TaskUpdateRequest dto) {
        if ( dto == null ) {
            return null;
        }

        TaskEntity taskEntity = new TaskEntity();

        if ( dto.getId() != null ) {
            taskEntity.setId( dto.getId() );
        }
        taskEntity.setName( dto.getName() );
        taskEntity.setDescription( dto.getDescription() );

        return taskEntity;
    }
}
