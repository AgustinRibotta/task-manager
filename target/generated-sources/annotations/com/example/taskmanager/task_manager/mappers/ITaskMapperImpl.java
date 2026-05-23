package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.ProjectResponseDto;
import com.example.taskmanager.task_manager.dtos.TaskDto;
import com.example.taskmanager.task_manager.dtos.UserDto;
import com.example.taskmanager.task_manager.entities.ProjectEntity;
import com.example.taskmanager.task_manager.entities.TaskEntity;
import com.example.taskmanager.task_manager.entities.UserEntity;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-23T22:08:19+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Ubuntu)"
)
@Component
public class ITaskMapperImpl implements ITaskMapper {

    @Override
    public TaskDto tasktEntityTopTaskDto(TaskEntity taskEntity) {
        if ( taskEntity == null ) {
            return null;
        }

        TaskDto taskDto = new TaskDto();

        taskDto.setProjectResponseDto( projectEntityToProjectResponseDto( taskEntity.getProjectEntity() ) );
        taskDto.setUserDto( userEntitySetToUserDtoSet( taskEntity.getUsers() ) );
        taskDto.setId( taskEntity.getId() );
        taskDto.setName( taskEntity.getName() );
        taskDto.setDescription( taskEntity.getDescription() );
        taskDto.setStatus( taskEntity.getStatus() );

        return taskDto;
    }

    @Override
    public TaskEntity tasktDtoToTaskEntity(TaskDto taskDto) {
        if ( taskDto == null ) {
            return null;
        }

        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setProjectEntity( projectResponseDtoToProjectEntity( taskDto.getProjectResponseDto() ) );
        taskEntity.setUsers( userDtoSetToUserEntitySet( taskDto.getUserDto() ) );
        if ( taskDto.getId() != null ) {
            taskEntity.setId( taskDto.getId() );
        }
        taskEntity.setName( taskDto.getName() );
        taskEntity.setDescription( taskDto.getDescription() );
        taskEntity.setStatus( taskDto.getStatus() );

        return taskEntity;
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

    protected UserEntity userDtoToUserEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( userDto.getId() );
        userEntity.setUsername( userDto.getUsername() );
        userEntity.setPassword( userDto.getPassword() );
        userEntity.setEmail( userDto.getEmail() );

        return userEntity;
    }

    protected Set<UserEntity> userDtoSetToUserEntitySet(Set<UserDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserEntity> set1 = new LinkedHashSet<UserEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UserDto userDto : set ) {
            set1.add( userDtoToUserEntity( userDto ) );
        }

        return set1;
    }
}
