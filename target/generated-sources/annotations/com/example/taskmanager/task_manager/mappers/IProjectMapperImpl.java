package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.ProjectRequestDto;
import com.example.taskmanager.task_manager.dtos.ProjectResponseDto;
import com.example.taskmanager.task_manager.dtos.TaskDto;
import com.example.taskmanager.task_manager.dtos.UserDto;
import com.example.taskmanager.task_manager.entities.ProjectEntity;
import com.example.taskmanager.task_manager.entities.TaskEntity;
import com.example.taskmanager.task_manager.entities.UserEntity;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-23T22:08:19+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Ubuntu)"
)
@Component
public class IProjectMapperImpl implements IProjectMapper {

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

        projectResponseDto.setTasksDto( taskEntityListToTaskDtoList( entity.getTaskEntities() ) );
        projectResponseDto.setUsersDto( userEntitySetToUserDtoSet( entity.getUsers() ) );
        projectResponseDto.setId( entity.getId() );
        projectResponseDto.setName( entity.getName() );
        projectResponseDto.setDescription( entity.getDescription() );

        return projectResponseDto;
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

    protected List<TaskDto> taskEntityListToTaskDtoList(List<TaskEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<TaskDto> list1 = new ArrayList<TaskDto>( list.size() );
        for ( TaskEntity taskEntity : list ) {
            list1.add( taskEntityToTaskDto( taskEntity ) );
        }

        return list1;
    }

    protected UserDto userEntityToUserDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( userEntity.getId() );
        userDto.setUsername( userEntity.getUsername() );
        userDto.setPassword( userEntity.getPassword() );
        userDto.setEmail( userEntity.getEmail() );

        return userDto;
    }

    protected Set<UserDto> userEntitySetToUserDtoSet(Set<UserEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserDto> set1 = new LinkedHashSet<UserDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UserEntity userEntity : set ) {
            set1.add( userEntityToUserDto( userEntity ) );
        }

        return set1;
    }
}
