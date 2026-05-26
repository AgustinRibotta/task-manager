package com.example.taskmanager.task_manager.mappers.task;

import com.example.taskmanager.task_manager.dtos.RoleDto;
import com.example.taskmanager.task_manager.dtos.task.TaskProjectDto;
import com.example.taskmanager.task_manager.dtos.task.TaskRequestDto;
import com.example.taskmanager.task_manager.dtos.task.TaskResponseDto;
import com.example.taskmanager.task_manager.dtos.task.TaskUserDto;
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
    date = "2026-05-26T21:15:50+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Ubuntu)"
)
@Component
public class ITaskMapperImpl implements ITaskMapper {

    @Override
    public TaskEntity tasktDtoToTaskEntity(TaskRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setName( dto.getName() );
        taskEntity.setDescription( dto.getDescription() );
        taskEntity.setStatus( dto.getStatus() );

        return taskEntity;
    }

    @Override
    public TaskResponseDto taskEntityTopTaskDto(TaskEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TaskResponseDto taskResponseDto = new TaskResponseDto();

        taskResponseDto.setId( entity.getId() );
        taskResponseDto.setName( entity.getName() );
        taskResponseDto.setDescription( entity.getDescription() );
        taskResponseDto.setProject( projectEntityToTaskProjectDto( entity.getProject() ) );
        taskResponseDto.setStatus( entity.getStatus() );
        taskResponseDto.setUsers( userEntitySetToTaskUserDtoSet( entity.getUsers() ) );

        return taskResponseDto;
    }

    protected TaskProjectDto projectEntityToTaskProjectDto(ProjectEntity projectEntity) {
        if ( projectEntity == null ) {
            return null;
        }

        TaskProjectDto taskProjectDto = new TaskProjectDto();

        taskProjectDto.setId( projectEntity.getId() );
        taskProjectDto.setName( projectEntity.getName() );

        return taskProjectDto;
    }

    protected RoleDto roleEntityToRoleDto(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( roleEntity.getId() );
        roleDto.setName( roleEntity.getName() );

        return roleDto;
    }

    protected Set<RoleDto> roleEntitySetToRoleDtoSet(Set<RoleEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDto> set1 = new LinkedHashSet<RoleDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleEntity roleEntity : set ) {
            set1.add( roleEntityToRoleDto( roleEntity ) );
        }

        return set1;
    }

    protected TaskUserDto userEntityToTaskUserDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        TaskUserDto taskUserDto = new TaskUserDto();

        taskUserDto.setId( userEntity.getId() );
        taskUserDto.setUsername( userEntity.getUsername() );
        taskUserDto.setEmail( userEntity.getEmail() );
        taskUserDto.setRoles( roleEntitySetToRoleDtoSet( userEntity.getRoles() ) );

        return taskUserDto;
    }

    protected Set<TaskUserDto> userEntitySetToTaskUserDtoSet(Set<UserEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<TaskUserDto> set1 = new LinkedHashSet<TaskUserDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UserEntity userEntity : set ) {
            set1.add( userEntityToTaskUserDto( userEntity ) );
        }

        return set1;
    }
}
