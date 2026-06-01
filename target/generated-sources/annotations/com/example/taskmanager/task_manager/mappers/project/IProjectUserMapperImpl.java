package com.example.taskmanager.task_manager.mappers.project;

import com.example.taskmanager.task_manager.dtos.project.ProjectRoleDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectUserDto;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import com.example.taskmanager.task_manager.entities.UserEntity;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-01T13:35:27+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Arch Linux)"
)
@Component
public class IProjectUserMapperImpl implements IProjectUserMapper {

    @Override
    public ProjectUserDto toProjectTaskDto(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectUserDto projectUserDto = new ProjectUserDto();

        projectUserDto.setRoles( roleEntitySetToProjectRoleDtoSet( entity.getRoles() ) );
        projectUserDto.setId( entity.getId() );
        projectUserDto.setUsername( entity.getUsername() );
        projectUserDto.setEmail( entity.getEmail() );

        return projectUserDto;
    }

    protected ProjectRoleDto roleEntityToProjectRoleDto(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        ProjectRoleDto projectRoleDto = new ProjectRoleDto();

        projectRoleDto.setId( roleEntity.getId() );
        projectRoleDto.setName( roleEntity.getName() );

        return projectRoleDto;
    }

    protected Set<ProjectRoleDto> roleEntitySetToProjectRoleDtoSet(Set<RoleEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<ProjectRoleDto> set1 = new LinkedHashSet<ProjectRoleDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleEntity roleEntity : set ) {
            set1.add( roleEntityToProjectRoleDto( roleEntity ) );
        }

        return set1;
    }
}
