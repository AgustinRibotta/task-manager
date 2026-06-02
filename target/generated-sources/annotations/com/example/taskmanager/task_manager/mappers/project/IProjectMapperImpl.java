package com.example.taskmanager.task_manager.mappers.project;

import com.example.taskmanager.task_manager.dtos.project.ProjectRequestDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectResponseDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectTaskDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectUserDto;
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
    date = "2026-06-02T10:39:43+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Arch Linux)"
)
@Component
public class IProjectMapperImpl implements IProjectMapper {

    @Autowired
    private IProjectTaskMapper iProjectTaskMapper;
    @Autowired
    private IProjectUserMapper iProjectUserMapper;

    @Override
    public ProjectEntity requestToEntity(ProjectRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        ProjectEntity projectEntity = new ProjectEntity();

        projectEntity.setName( dto.getName() );
        projectEntity.setDescription( dto.getDescription() );

        return projectEntity;
    }

    @Override
    public ProjectResponseDto entityToResponse(ProjectEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectResponseDto projectResponseDto = new ProjectResponseDto();

        projectResponseDto.setTasks( taskEntityListToProjectTaskDtoList( entity.getTasks() ) );
        projectResponseDto.setUsers( userEntitySetToProjectUserDtoSet( entity.getUsers() ) );
        projectResponseDto.setOwner( iProjectUserMapper.toProjectTaskDto( entity.getOwner() ) );
        projectResponseDto.setId( entity.getId() );
        projectResponseDto.setName( entity.getName() );
        projectResponseDto.setDescription( entity.getDescription() );

        return projectResponseDto;
    }

    protected List<ProjectTaskDto> taskEntityListToProjectTaskDtoList(List<TaskEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<ProjectTaskDto> list1 = new ArrayList<ProjectTaskDto>( list.size() );
        for ( TaskEntity taskEntity : list ) {
            list1.add( iProjectTaskMapper.toProjectTaskDto( taskEntity ) );
        }

        return list1;
    }

    protected Set<ProjectUserDto> userEntitySetToProjectUserDtoSet(Set<UserEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<ProjectUserDto> set1 = new LinkedHashSet<ProjectUserDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UserEntity userEntity : set ) {
            set1.add( iProjectUserMapper.toProjectTaskDto( userEntity ) );
        }

        return set1;
    }
}
