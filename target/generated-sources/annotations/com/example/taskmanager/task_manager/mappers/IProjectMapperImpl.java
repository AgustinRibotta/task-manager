package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.ProjectDto;
import com.example.taskmanager.task_manager.dtos.TaskSummaryDto;
import com.example.taskmanager.task_manager.dtos.UserSummaryDto;
import com.example.taskmanager.task_manager.entities.ProjectEntity;
import com.example.taskmanager.task_manager.entities.TaskEntity;
import com.example.taskmanager.task_manager.entities.UserEntity;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-17T14:07:55+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Ubuntu)"
)
@Component
public class IProjectMapperImpl implements IProjectMapper {

    @Autowired
    private IUserForProjectMapper iUserForProjectMapper;

    @Override
    public ProjectDto projectEntityToProjecDto(ProjectEntity projectEntity) {
        if ( projectEntity == null ) {
            return null;
        }

        ProjectDto projectDto = new ProjectDto();

        projectDto.setUsersDtos( userEntitySetToUserSummaryDtoSet( projectEntity.getUsers() ) );
        projectDto.setTasksDtos( taskEntityListToTaskSummaryDtoList( projectEntity.getTaskEntities() ) );
        projectDto.setId( projectEntity.getId() );
        projectDto.setName( projectEntity.getName() );
        projectDto.setDescription( projectEntity.getDescription() );

        return projectDto;
    }

    @Override
    public ProjectEntity projectDtoToProjectEntity(ProjectDto projecDto) {
        if ( projecDto == null ) {
            return null;
        }

        ProjectEntity projectEntity = new ProjectEntity();

        projectEntity.setUsers( userSummaryDtoSetToUserEntitySet( projecDto.getUsersDtos() ) );
        projectEntity.setTaskEntities( taskSummaryDtoListToTaskEntityList( projecDto.getTasksDtos() ) );
        projectEntity.setName( projecDto.getName() );
        projectEntity.setDescription( projecDto.getDescription() );

        return projectEntity;
    }

    protected Set<UserSummaryDto> userEntitySetToUserSummaryDtoSet(Set<UserEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserSummaryDto> set1 = new LinkedHashSet<UserSummaryDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UserEntity userEntity : set ) {
            set1.add( iUserForProjectMapper.userEntityToUserDtoForProjectDto( userEntity ) );
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

    protected List<TaskSummaryDto> taskEntityListToTaskSummaryDtoList(List<TaskEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<TaskSummaryDto> list1 = new ArrayList<TaskSummaryDto>( list.size() );
        for ( TaskEntity taskEntity : list ) {
            list1.add( taskEntityToTaskSummaryDto( taskEntity ) );
        }

        return list1;
    }

    protected Set<UserEntity> userSummaryDtoSetToUserEntitySet(Set<UserSummaryDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserEntity> set1 = new LinkedHashSet<UserEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UserSummaryDto userSummaryDto : set ) {
            set1.add( iUserForProjectMapper.userDtoForProjectDtoToUserEntity( userSummaryDto ) );
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

    protected List<TaskEntity> taskSummaryDtoListToTaskEntityList(List<TaskSummaryDto> list) {
        if ( list == null ) {
            return null;
        }

        List<TaskEntity> list1 = new ArrayList<TaskEntity>( list.size() );
        for ( TaskSummaryDto taskSummaryDto : list ) {
            list1.add( taskSummaryDtoToTaskEntity( taskSummaryDto ) );
        }

        return list1;
    }
}
