package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.ProjectSummaryDto;
import com.example.taskmanager.task_manager.entities.ProjectEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-17T11:42:22+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Ubuntu)"
)
@Component
public class IProjectForUserMapperImpl implements IProjectForUserMapper {

    @Override
    public ProjectSummaryDto projectEntityToProjectDtoForUserDto(ProjectEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectSummaryDto projectSummaryDto = new ProjectSummaryDto();

        projectSummaryDto.setId( entity.getId() );
        projectSummaryDto.setName( entity.getName() );

        return projectSummaryDto;
    }

    @Override
    public ProjectEntity projectDtoForUserDtoToProjectEntity(ProjectSummaryDto dto) {
        if ( dto == null ) {
            return null;
        }

        ProjectEntity projectEntity = new ProjectEntity();

        if ( dto.getId() != null ) {
            projectEntity.setId( dto.getId() );
        }
        projectEntity.setName( dto.getName() );

        return projectEntity;
    }
}
