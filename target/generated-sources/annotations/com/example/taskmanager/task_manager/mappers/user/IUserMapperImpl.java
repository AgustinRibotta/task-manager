package com.example.taskmanager.task_manager.mappers.user;

import com.example.taskmanager.task_manager.dtos.permission.PermissionDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectSummaryDto;
import com.example.taskmanager.task_manager.dtos.role.RoleResponseDto;
import com.example.taskmanager.task_manager.dtos.task.TaskSummaryDto;
import com.example.taskmanager.task_manager.dtos.user.UserRequestDto;
import com.example.taskmanager.task_manager.dtos.user.UserResponseDto;
import com.example.taskmanager.task_manager.entities.PermissionEntity;
import com.example.taskmanager.task_manager.entities.ProjectEntity;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import com.example.taskmanager.task_manager.entities.TaskEntity;
import com.example.taskmanager.task_manager.entities.UserEntity;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-20T14:23:06+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Arch Linux)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public UserEntity toEntity(UserRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( dto.getUsername() );
        userEntity.setPassword( dto.getPassword() );
        userEntity.setEmail( dto.getEmail() );

        return userEntity;
    }

    @Override
    public UserResponseDto toDto(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( entity.getId() );
        userResponseDto.setUsername( entity.getUsername() );
        userResponseDto.setEmail( entity.getEmail() );
        userResponseDto.setRoles( roleEntitySetToRoleResponseDtoSet( entity.getRoles() ) );
        userResponseDto.setProjects( projectEntitySetToProjectSummaryDtoSet( entity.getProjects() ) );
        userResponseDto.setTasks( taskEntitySetToTaskSummaryDtoSet( entity.getTasks() ) );

        return userResponseDto;
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

    protected RoleResponseDto roleEntityToRoleResponseDto(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RoleResponseDto roleResponseDto = new RoleResponseDto();

        roleResponseDto.setId( roleEntity.getId() );
        roleResponseDto.setName( roleEntity.getName() );
        roleResponseDto.setPermissions( permissionEntitySetToPermissionDtoSet( roleEntity.getPermissions() ) );

        return roleResponseDto;
    }

    protected Set<RoleResponseDto> roleEntitySetToRoleResponseDtoSet(Set<RoleEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleResponseDto> set1 = new LinkedHashSet<RoleResponseDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleEntity roleEntity : set ) {
            set1.add( roleEntityToRoleResponseDto( roleEntity ) );
        }

        return set1;
    }

    protected ProjectSummaryDto projectEntityToProjectSummaryDto(ProjectEntity projectEntity) {
        if ( projectEntity == null ) {
            return null;
        }

        ProjectSummaryDto projectSummaryDto = new ProjectSummaryDto();

        projectSummaryDto.setId( projectEntity.getId() );
        projectSummaryDto.setName( projectEntity.getName() );

        return projectSummaryDto;
    }

    protected Set<ProjectSummaryDto> projectEntitySetToProjectSummaryDtoSet(Set<ProjectEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<ProjectSummaryDto> set1 = new LinkedHashSet<ProjectSummaryDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ProjectEntity projectEntity : set ) {
            set1.add( projectEntityToProjectSummaryDto( projectEntity ) );
        }

        return set1;
    }

    protected TaskSummaryDto taskEntityToTaskSummaryDto(TaskEntity taskEntity) {
        if ( taskEntity == null ) {
            return null;
        }

        TaskSummaryDto taskSummaryDto = new TaskSummaryDto();

        taskSummaryDto.setId( taskEntity.getId() );
        taskSummaryDto.setName( taskEntity.getName() );
        taskSummaryDto.setStatus( taskEntity.getStatus() );

        return taskSummaryDto;
    }

    protected Set<TaskSummaryDto> taskEntitySetToTaskSummaryDtoSet(Set<TaskEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<TaskSummaryDto> set1 = new LinkedHashSet<TaskSummaryDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TaskEntity taskEntity : set ) {
            set1.add( taskEntityToTaskSummaryDto( taskEntity ) );
        }

        return set1;
    }
}
