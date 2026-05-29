package com.example.taskmanager.task_manager.mappers.project;

import com.example.taskmanager.task_manager.dtos.RoleDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectUserDto;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import com.example.taskmanager.task_manager.entities.UserEntity;
import com.example.taskmanager.task_manager.mappers.IRoleMapper;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-28T21:16:03+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Arch Linux)"
)
@Component
public class IProjectUserMapperImpl implements IProjectUserMapper {

    @Autowired
    private IRoleMapper iRoleMapper;

    @Override
    public ProjectUserDto toProjectTaskDto(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectUserDto projectUserDto = new ProjectUserDto();

        projectUserDto.setRoles( roleEntitySetToRoleDtoSet( entity.getRoles() ) );
        projectUserDto.setId( entity.getId() );
        projectUserDto.setUsername( entity.getUsername() );
        projectUserDto.setEmail( entity.getEmail() );

        return projectUserDto;
    }

    protected Set<RoleDto> roleEntitySetToRoleDtoSet(Set<RoleEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDto> set1 = new LinkedHashSet<RoleDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleEntity roleEntity : set ) {
            set1.add( iRoleMapper.roleEntityToRoleDto( roleEntity ) );
        }

        return set1;
    }
}
