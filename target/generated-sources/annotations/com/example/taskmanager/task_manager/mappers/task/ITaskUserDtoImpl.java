package com.example.taskmanager.task_manager.mappers.task;

import com.example.taskmanager.task_manager.dtos.RoleDto;
import com.example.taskmanager.task_manager.dtos.project.PermissionDto;
import com.example.taskmanager.task_manager.dtos.task.TaskUserDto;
import com.example.taskmanager.task_manager.entities.PermissionEntity;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import com.example.taskmanager.task_manager.entities.UserEntity;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-02T10:39:43+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Arch Linux)"
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

    protected PermissionDto permissionEntityToPermissionDto(PermissionEntity permissionEntity) {
        if ( permissionEntity == null ) {
            return null;
        }

        PermissionDto permissionDto = new PermissionDto();

        permissionDto.setId( permissionEntity.getId() );
        permissionDto.setName( permissionEntity.getName() );

        return permissionDto;
    }

    protected Set<PermissionDto> permissionEntitySetToPermissionDtoSet(Set<PermissionEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<PermissionDto> set1 = new LinkedHashSet<PermissionDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PermissionEntity permissionEntity : set ) {
            set1.add( permissionEntityToPermissionDto( permissionEntity ) );
        }

        return set1;
    }

    protected RoleDto roleEntityToRoleDto(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( roleEntity.getId() );
        roleDto.setName( roleEntity.getName() );
        roleDto.setPermissions( permissionEntitySetToPermissionDtoSet( roleEntity.getPermissions() ) );

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
