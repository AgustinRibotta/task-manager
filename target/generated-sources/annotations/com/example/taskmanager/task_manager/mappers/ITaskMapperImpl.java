package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.ProjectSummaryDto;
import com.example.taskmanager.task_manager.dtos.TaskDto;
import com.example.taskmanager.task_manager.dtos.UserSummaryDto;
import com.example.taskmanager.task_manager.entities.ProjectEntity;
import com.example.taskmanager.task_manager.entities.TaskEntity;
import com.example.taskmanager.task_manager.entities.UserEntity;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-17T14:07:55+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Ubuntu)"
)
@Component
public class ITaskMapperImpl implements ITaskMapper {

    @Override
    public TaskDto tasktEntityTopTaskDto(TaskEntity tasktEntity) {
        if ( tasktEntity == null ) {
            return null;
        }

        TaskDto taskDto = new TaskDto();

        taskDto.setProjectSummaryDto( projectEntityToProjectSummaryDto( tasktEntity.getProjectEntity() ) );
        taskDto.setUserSummaryDto( userEntitySetToUserSummaryDtoSet( tasktEntity.getUsers() ) );
        taskDto.setId( tasktEntity.getId() );
        taskDto.setName( tasktEntity.getName() );
        taskDto.setDescription( tasktEntity.getDescription() );
        taskDto.setStatus( tasktEntity.getStatus() );

        return taskDto;
    }

    @Override
    public TaskEntity tasktDtoToTaskEntity(TaskDto taskDto) {
        if ( taskDto == null ) {
            return null;
        }

        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setProjectEntity( projectSummaryDtoToProjectEntity( taskDto.getProjectSummaryDto() ) );
        taskEntity.setUsers( userSummaryDtoSetToUserEntitySet( taskDto.getUserSummaryDto() ) );
        if ( taskDto.getId() != null ) {
            taskEntity.setId( taskDto.getId() );
        }
        taskEntity.setName( taskDto.getName() );
        taskEntity.setDescription( taskDto.getDescription() );
        taskEntity.setStatus( taskDto.getStatus() );

        return taskEntity;
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

    protected UserSummaryDto userEntityToUserSummaryDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserSummaryDto userSummaryDto = new UserSummaryDto();

        userSummaryDto.setId( userEntity.getId() );
        userSummaryDto.setUsername( userEntity.getUsername() );

        return userSummaryDto;
    }

    protected Set<UserSummaryDto> userEntitySetToUserSummaryDtoSet(Set<UserEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserSummaryDto> set1 = new LinkedHashSet<UserSummaryDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UserEntity userEntity : set ) {
            set1.add( userEntityToUserSummaryDto( userEntity ) );
        }

        return set1;
    }

    protected ProjectEntity projectSummaryDtoToProjectEntity(ProjectSummaryDto projectSummaryDto) {
        if ( projectSummaryDto == null ) {
            return null;
        }

        ProjectEntity projectEntity = new ProjectEntity();

        if ( projectSummaryDto.getId() != null ) {
            projectEntity.setId( projectSummaryDto.getId() );
        }
        projectEntity.setName( projectSummaryDto.getName() );

        return projectEntity;
    }

    protected UserEntity userSummaryDtoToUserEntity(UserSummaryDto userSummaryDto) {
        if ( userSummaryDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( userSummaryDto.getId() );
        userEntity.setUsername( userSummaryDto.getUsername() );

        return userEntity;
    }

    protected Set<UserEntity> userSummaryDtoSetToUserEntitySet(Set<UserSummaryDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserEntity> set1 = new LinkedHashSet<UserEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UserSummaryDto userSummaryDto : set ) {
            set1.add( userSummaryDtoToUserEntity( userSummaryDto ) );
        }

        return set1;
    }
}
