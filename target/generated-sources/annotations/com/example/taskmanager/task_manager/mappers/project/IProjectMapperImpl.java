package com.example.taskmanager.task_manager.mappers.project;

import com.example.taskmanager.task_manager.dtos.project.ProjectRequestDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectResponseDto;
import com.example.taskmanager.task_manager.dtos.task.TaskSummaryDto;
import com.example.taskmanager.task_manager.dtos.user.UserSummaryDto;
import com.example.taskmanager.task_manager.entities.ProjectEntity;
import com.example.taskmanager.task_manager.entities.TaskEntity;
import com.example.taskmanager.task_manager.entities.UserEntity;
import com.example.taskmanager.task_manager.mappers.task.ITaskSummaryMapper;
import com.example.taskmanager.task_manager.mappers.user.IUserSummaryMapper;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-13T22:40:51+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Arch Linux)"
)
@Component
public class IProjectMapperImpl implements IProjectMapper {

    @Autowired
    private ITaskSummaryMapper iTaskSummaryMapper;
    @Autowired
    private IUserSummaryMapper iUserSummaryMapper;

    @Override
    public ProjectEntity toEntity(ProjectRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        ProjectEntity projectEntity = new ProjectEntity();

        projectEntity.setName( dto.getName() );
        projectEntity.setDescription( dto.getDescription() );

        return projectEntity;
    }

    @Override
    public ProjectResponseDto toDto(ProjectEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectResponseDto projectResponseDto = new ProjectResponseDto();

        projectResponseDto.setTasks( taskEntityListToTaskSummaryDtoList( entity.getTasks() ) );
        projectResponseDto.setUsers( userEntitySetToUserSummaryDtoSet( entity.getUsers() ) );
        projectResponseDto.setOwner( iUserSummaryMapper.toDto( entity.getOwner() ) );
        projectResponseDto.setId( entity.getId() );
        projectResponseDto.setName( entity.getName() );
        projectResponseDto.setDescription( entity.getDescription() );

        return projectResponseDto;
    }

    protected List<TaskSummaryDto> taskEntityListToTaskSummaryDtoList(List<TaskEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<TaskSummaryDto> list1 = new ArrayList<TaskSummaryDto>( list.size() );
        for ( TaskEntity taskEntity : list ) {
            list1.add( iTaskSummaryMapper.toDto( taskEntity ) );
        }

        return list1;
    }

    protected Set<UserSummaryDto> userEntitySetToUserSummaryDtoSet(Set<UserEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserSummaryDto> set1 = new LinkedHashSet<UserSummaryDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UserEntity userEntity : set ) {
            set1.add( iUserSummaryMapper.toDto( userEntity ) );
        }

        return set1;
    }
}
