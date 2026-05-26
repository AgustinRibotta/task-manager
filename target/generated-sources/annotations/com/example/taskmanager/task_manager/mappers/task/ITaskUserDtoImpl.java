package com.example.taskmanager.task_manager.mappers.task;

import com.example.taskmanager.task_manager.dtos.RoleDto;
import com.example.taskmanager.task_manager.dtos.task.TaskUserDto;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import com.example.taskmanager.task_manager.entities.UserEntity;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-26T21:15:50+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Ubuntu)"
)
@Component
public class ITaskUserDtoImpl implements ITaskUserDto {

    @Override
    public TaskUserDto toToTaskUserDto(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TaskUserDto taskUserDto = new TaskUserDto();

        taskUserDto.setId( entity.getId() );
        taskUserDto.setUsername( entity.getUsername() );
        taskUserDto.setEmail( entity.getEmail() );
        taskUserDto.setRoles( roleEntitySetToRoleDtoSet( entity.getRoles() ) );

        return taskUserDto;
    }

    protected RoleDto roleEntityToRoleDto(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( roleEntity.getId() );
        roleDto.setName( roleEntity.getName() );

        return roleDto;
    }

    protected Set<RoleDto> roleEntitySetToRoleDtoSet(Set<RoleEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDto> set1 = new LinkedHashSet<RoleDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleEntity roleEntity : set ) {
            set1.add( roleEntityToRoleDto( roleEntity ) );
        }

        return set1;
    }
}
