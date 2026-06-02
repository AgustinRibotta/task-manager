package com.example.taskmanager.task_manager.mappers.project;

import com.example.taskmanager.task_manager.dtos.project.ProjectRoleDto;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-02T10:39:43+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Arch Linux)"
)
@Component
public class IProjectRoleMapperImpl implements IProjectRoleMapper {

    @Override
    public ProjectRoleDto toProjectRoeDto(RoleEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectRoleDto projectRoleDto = new ProjectRoleDto();

        projectRoleDto.setId( entity.getId() );
        projectRoleDto.setName( entity.getName() );

        return projectRoleDto;
    }
}
