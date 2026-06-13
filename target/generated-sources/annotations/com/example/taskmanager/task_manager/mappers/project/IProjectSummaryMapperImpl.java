package com.example.taskmanager.task_manager.mappers.project;

import com.example.taskmanager.task_manager.dtos.project.ProjectSummaryDto;
import com.example.taskmanager.task_manager.entities.ProjectEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-13T22:40:51+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Arch Linux)"
)
@Component
public class IProjectSummaryMapperImpl implements IProjectSummaryMapper {

    @Override
    public ProjectSummaryDto toDto(ProjectEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectSummaryDto projectSummaryDto = new ProjectSummaryDto();

        projectSummaryDto.setId( entity.getId() );
        projectSummaryDto.setName( entity.getName() );

        return projectSummaryDto;
    }
}
