package com.example.taskmanager.task_manager.mappers.user;

import com.example.taskmanager.task_manager.dtos.project.ProjectSummaryDto;
import com.example.taskmanager.task_manager.dtos.role.RoleSummaryDto;
import com.example.taskmanager.task_manager.dtos.task.TaskSummaryDto;
import com.example.taskmanager.task_manager.dtos.user.UserRequestDto;
import com.example.taskmanager.task_manager.dtos.user.UserResponseDto;
import com.example.taskmanager.task_manager.entities.ProjectEntity;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import com.example.taskmanager.task_manager.entities.TaskEntity;
import com.example.taskmanager.task_manager.entities.UserEntity;
import com.example.taskmanager.task_manager.mappers.role.IRoleSummaryMapper;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-14T15:20:16+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Arch Linux)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Autowired
    private IRoleSummaryMapper iRoleSummaryMapper;

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
        userResponseDto.setRoles( roleEntitySetToRoleSummaryDtoSet( entity.getRoles() ) );
        userResponseDto.setProjects( projectEntitySetToProjectSummaryDtoSet( entity.getProjects() ) );
        userResponseDto.setTasks( taskEntitySetToTaskSummaryDtoSet( entity.getTasks() ) );

        return userResponseDto;
    }

    protected Set<RoleSummaryDto> roleEntitySetToRoleSummaryDtoSet(Set<RoleEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleSummaryDto> set1 = new LinkedHashSet<RoleSummaryDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleEntity roleEntity : set ) {
            set1.add( iRoleSummaryMapper.toDto( roleEntity ) );
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
