package com.example.taskmanager.task_manager.mappers.task;

import com.example.taskmanager.task_manager.dtos.role.RoleRequestDto;
import com.example.taskmanager.task_manager.dtos.task.TaskRequestDto;
import com.example.taskmanager.task_manager.dtos.task.TaskResponseDto;
import com.example.taskmanager.task_manager.dtos.task.TaskSummaryDto;
import com.example.taskmanager.task_manager.dtos.user.UserSummaryDto;
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
    date = "2026-06-13T22:40:51+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Arch Linux)"
)
@Component
public class ITaskMapperImpl implements ITaskMapper {

    @Override
    public TaskEntity toEntity(TaskRequestDto dto) {
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
    public TaskResponseDto toDto(TaskEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TaskResponseDto taskResponseDto = new TaskResponseDto();

        taskResponseDto.setId( entity.getId() );
        taskResponseDto.setName( entity.getName() );
        taskResponseDto.setDescription( entity.getDescription() );
        taskResponseDto.setProject( projectEntityToTaskSummaryDto( entity.getProject() ) );
        taskResponseDto.setStatus( entity.getStatus() );
        taskResponseDto.setUsers( userEntitySetToUserSummaryDtoSet( entity.getUsers() ) );

        return taskResponseDto;
    }

    protected TaskSummaryDto projectEntityToTaskSummaryDto(ProjectEntity projectEntity) {
        if ( projectEntity == null ) {
            return null;
        }

        TaskSummaryDto taskSummaryDto = new TaskSummaryDto();

        taskSummaryDto.setId( projectEntity.getId() );
        taskSummaryDto.setName( projectEntity.getName() );

        return taskSummaryDto;
    }

    protected RoleRequestDto roleEntityToRoleRequestDto(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RoleRequestDto roleRequestDto = new RoleRequestDto();

        roleRequestDto.setId( roleEntity.getId() );
        roleRequestDto.setName( roleEntity.getName() );

        return roleRequestDto;
    }

    protected Set<RoleRequestDto> roleEntitySetToRoleRequestDtoSet(Set<RoleEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleRequestDto> set1 = new LinkedHashSet<RoleRequestDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleEntity roleEntity : set ) {
            set1.add( roleEntityToRoleRequestDto( roleEntity ) );
        }

        return set1;
    }

    protected UserSummaryDto userEntityToUserSummaryDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserSummaryDto userSummaryDto = new UserSummaryDto();

        userSummaryDto.setId( userEntity.getId() );
        userSummaryDto.setUsername( userEntity.getUsername() );
        userSummaryDto.setEmail( userEntity.getEmail() );
        userSummaryDto.setRoles( roleEntitySetToRoleRequestDtoSet( userEntity.getRoles() ) );

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
}
