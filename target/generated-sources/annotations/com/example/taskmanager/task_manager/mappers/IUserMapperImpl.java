package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.ProjectResponseDto;
import com.example.taskmanager.task_manager.dtos.RoleDto;
import com.example.taskmanager.task_manager.dtos.TaskDto;
import com.example.taskmanager.task_manager.dtos.UserDto;
import com.example.taskmanager.task_manager.entities.ProjectEntity;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import com.example.taskmanager.task_manager.entities.TaskEntity;
import com.example.taskmanager.task_manager.entities.UserEntity;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-24T23:06:43+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Ubuntu)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Autowired
    private IRoleMapper iRoleMapper;

    @Override
    public UserDto userToUserDto(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setProjectResponseDto( projectEntitySetToProjectResponseDtoSet( user.getProjectEntities() ) );
        userDto.setRoleDto( roleEntitySetToRoleDtoSet( user.getRoleEntities() ) );
        userDto.setTaskDto( taskEntitySetToTaskDtoSet( user.getTaskEntities() ) );
        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setPassword( user.getPassword() );
        userDto.setEmail( user.getEmail() );

        return userDto;
    }

    @Override
    public UserEntity userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setProjectEntities( projectResponseDtoSetToProjectEntitySet( userDto.getProjectResponseDto() ) );
        userEntity.setRoleEntities( roleDtoSetToRoleEntitySet( userDto.getRoleDto() ) );
        userEntity.setTaskEntities( taskDtoSetToTaskEntitySet( userDto.getTaskDto() ) );
        userEntity.setId( userDto.getId() );
        userEntity.setUsername( userDto.getUsername() );
        userEntity.setPassword( userDto.getPassword() );
        userEntity.setEmail( userDto.getEmail() );

        return userEntity;
    }

    protected ProjectResponseDto projectEntityToProjectResponseDto(ProjectEntity projectEntity) {
        if ( projectEntity == null ) {
            return null;
        }

        ProjectResponseDto projectResponseDto = new ProjectResponseDto();

        projectResponseDto.setId( projectEntity.getId() );
        projectResponseDto.setName( projectEntity.getName() );
        projectResponseDto.setDescription( projectEntity.getDescription() );

        return projectResponseDto;
    }

    protected Set<ProjectResponseDto> projectEntitySetToProjectResponseDtoSet(Set<ProjectEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<ProjectResponseDto> set1 = new LinkedHashSet<ProjectResponseDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ProjectEntity projectEntity : set ) {
            set1.add( projectEntityToProjectResponseDto( projectEntity ) );
        }

        return set1;
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

    protected TaskDto taskEntityToTaskDto(TaskEntity taskEntity) {
        if ( taskEntity == null ) {
            return null;
        }

        TaskDto taskDto = new TaskDto();

        taskDto.setId( taskEntity.getId() );
        taskDto.setName( taskEntity.getName() );
        taskDto.setDescription( taskEntity.getDescription() );
        taskDto.setStatus( taskEntity.getStatus() );

        return taskDto;
    }

    protected Set<TaskDto> taskEntitySetToTaskDtoSet(Set<TaskEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<TaskDto> set1 = new LinkedHashSet<TaskDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TaskEntity taskEntity : set ) {
            set1.add( taskEntityToTaskDto( taskEntity ) );
        }

        return set1;
    }

    protected ProjectEntity projectResponseDtoToProjectEntity(ProjectResponseDto projectResponseDto) {
        if ( projectResponseDto == null ) {
            return null;
        }

        ProjectEntity projectEntity = new ProjectEntity();

        if ( projectResponseDto.getId() != null ) {
            projectEntity.setId( projectResponseDto.getId() );
        }
        projectEntity.setName( projectResponseDto.getName() );
        projectEntity.setDescription( projectResponseDto.getDescription() );

        return projectEntity;
    }

    protected Set<ProjectEntity> projectResponseDtoSetToProjectEntitySet(Set<ProjectResponseDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<ProjectEntity> set1 = new LinkedHashSet<ProjectEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ProjectResponseDto projectResponseDto : set ) {
            set1.add( projectResponseDtoToProjectEntity( projectResponseDto ) );
        }

        return set1;
    }

    protected Set<RoleEntity> roleDtoSetToRoleEntitySet(Set<RoleDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleEntity> set1 = new LinkedHashSet<RoleEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleDto roleDto : set ) {
            set1.add( iRoleMapper.roleDtoToRoleEntity( roleDto ) );
        }

        return set1;
    }

    protected TaskEntity taskDtoToTaskEntity(TaskDto taskDto) {
        if ( taskDto == null ) {
            return null;
        }

        TaskEntity taskEntity = new TaskEntity();

        if ( taskDto.getId() != null ) {
            taskEntity.setId( taskDto.getId() );
        }
        taskEntity.setName( taskDto.getName() );
        taskEntity.setDescription( taskDto.getDescription() );
        taskEntity.setStatus( taskDto.getStatus() );

        return taskEntity;
    }

    protected Set<TaskEntity> taskDtoSetToTaskEntitySet(Set<TaskDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<TaskEntity> set1 = new LinkedHashSet<TaskEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TaskDto taskDto : set ) {
            set1.add( taskDtoToTaskEntity( taskDto ) );
        }

        return set1;
    }
}
