package com.example.taskmanager.task_manager.mappers.task;

import com.example.taskmanager.task_manager.dtos.task.TaskSummaryDto;
import com.example.taskmanager.task_manager.entities.TaskEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-20T14:23:06+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Arch Linux)"
)
@Component
public class ITaskSummaryMapperImpl implements ITaskSummaryMapper {

    @Override
    public TaskSummaryDto toDto(TaskEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TaskSummaryDto taskSummaryDto = new TaskSummaryDto();

        taskSummaryDto.setId( entity.getId() );
        taskSummaryDto.setName( entity.getName() );
        taskSummaryDto.setStatus( entity.getStatus() );

        return taskSummaryDto;
    }
}
