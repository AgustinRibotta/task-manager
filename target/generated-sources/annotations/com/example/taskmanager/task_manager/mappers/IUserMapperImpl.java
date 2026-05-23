package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.ProjectSummaryDto;
import com.example.taskmanager.task_manager.dtos.RoleDto;
import com.example.taskmanager.task_manager.dtos.TaskSummaryDto;
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
    date = "2026-05-23T10:05:31+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Ubuntu)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Autowired
    private IRoleMapper iRoleMapper;
    @Autowired
    private IProjectForUserMapper iProjectForUserMapper;

    @Override
    public UserDto userToUserDto(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setProjecDtos( projectEntitySetToProjectSummaryDtoSet( user.getProjectEntities() ) );
        userDto.setRoleDtos( roleEntitySetToRoleDtoSet( user.getRoleEntities() ) );
        userDto.setTaskDto( taskEntitySetToTaskSummaryDtoSet( user.getTaskEntities() ) );
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

        userEntity.setProjectEntities( projectSummaryDtoSetToProjectEntitySet( userDto.getProjecDtos() ) );
        userEntity.setRoleEntities( roleDtoSetToRoleEntitySet( userDto.getRoleDtos() ) );
        userEntity.setTaskEntities( taskSummaryDtoSetToTaskEntitySet( userDto.getTaskDto() ) );
        userEntity.setId( userDto.getId() );
        userEntity.setUsername( userDto.getUsername() );
        userEntity.setPassword( userDto.getPassword() );
        userEntity.setEmail( userDto.getEmail() );

        return userEntity;
    }

    protected Set<ProjectSummaryDto> projectEntitySetToProjectSummaryDtoSet(Set<ProjectEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<ProjectSummaryDto> set1 = new LinkedHashSet<ProjectSummaryDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ProjectEntity projectEntity : set ) {
            set1.add( iProjectForUserMapper.projectEntityToProjectDtoForUserDto( projectEntity ) );
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

    protected TaskSummaryDto taskEntityToTaskSummaryDto(TaskEntity taskEntity) {
        if ( taskEntity == null ) {
            return null;
        }

        TaskSummaryDto taskSummaryDto = new TaskSummaryDto();

        taskSummaryDto.setId( taskEntity.getId() );
        taskSummaryDto.setName( taskEntity.getName() );

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

    protected Set<ProjectEntity> projectSummaryDtoSetToProjectEntitySet(Set<ProjectSummaryDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<ProjectEntity> set1 = new LinkedHashSet<ProjectEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ProjectSummaryDto projectSummaryDto : set ) {
            set1.add( iProjectForUserMapper.projectDtoForUserDtoToProjectEntity( projectSummaryDto ) );
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

    protected TaskEntity taskSummaryDtoToTaskEntity(TaskSummaryDto taskSummaryDto) {
        if ( taskSummaryDto == null ) {
            return null;
        }

        TaskEntity taskEntity = new TaskEntity();

        if ( taskSummaryDto.getId() != null ) {
            taskEntity.setId( taskSummaryDto.getId() );
        }
        taskEntity.setName( taskSummaryDto.getName() );

        return taskEntity;
    }

    protected Set<TaskEntity> taskSummaryDtoSetToTaskEntitySet(Set<TaskSummaryDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<TaskEntity> set1 = new LinkedHashSet<TaskEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TaskSummaryDto taskSummaryDto : set ) {
            set1.add( taskSummaryDtoToTaskEntity( taskSummaryDto ) );
        }

        return set1;
    }
}
