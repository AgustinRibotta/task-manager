package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.RoleDto;
import com.example.taskmanager.task_manager.dtos.UserDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectResponseDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectRoleDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectTaskDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectUserDto;
import com.example.taskmanager.task_manager.dtos.task.TaskProjectDto;
import com.example.taskmanager.task_manager.dtos.task.TaskResponseDto;
import com.example.taskmanager.task_manager.dtos.task.TaskUserDto;
import com.example.taskmanager.task_manager.entities.ProjectEntity;
import com.example.taskmanager.task_manager.entities.RoleEntity;
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
    date = "2026-05-30T15:25:23+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Arch Linux)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Autowired
    private IRoleMapper iRoleMapper;

    @Override
    public UserDto userToUserDto(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setPassword( user.getPassword() );
        userDto.setEmail( user.getEmail() );
        userDto.setRoles( roleEntitySetToRoleDtoSet( user.getRoles() ) );
        userDto.setProjects( projectEntitySetToProjectResponseDtoSet( user.getProjects() ) );
        userDto.setTasks( taskEntitySetToTaskResponseDtoSet( user.getTasks() ) );

        return userDto;
    }

    @Override
    public UserEntity userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( userDto.getId() );
        userEntity.setUsername( userDto.getUsername() );
        userEntity.setPassword( userDto.getPassword() );
        userEntity.setEmail( userDto.getEmail() );
        userEntity.setRoles( roleDtoSetToRoleEntitySet( userDto.getRoles() ) );
        userEntity.setProjects( projectResponseDtoSetToProjectEntitySet( userDto.getProjects() ) );
        userEntity.setTasks( taskResponseDtoSetToTaskEntitySet( userDto.getTasks() ) );

        return userEntity;
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

    protected ProjectRoleDto roleEntityToProjectRoleDto(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        ProjectRoleDto projectRoleDto = new ProjectRoleDto();

        projectRoleDto.setId( roleEntity.getId() );
        projectRoleDto.setName( roleEntity.getName() );

        return projectRoleDto;
    }

    protected Set<ProjectRoleDto> roleEntitySetToProjectRoleDtoSet(Set<RoleEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<ProjectRoleDto> set1 = new LinkedHashSet<ProjectRoleDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleEntity roleEntity : set ) {
            set1.add( roleEntityToProjectRoleDto( roleEntity ) );
        }

        return set1;
    }

    protected ProjectUserDto userEntityToProjectUserDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        ProjectUserDto projectUserDto = new ProjectUserDto();

        projectUserDto.setId( userEntity.getId() );
        projectUserDto.setUsername( userEntity.getUsername() );
        projectUserDto.setEmail( userEntity.getEmail() );
        projectUserDto.setRoles( roleEntitySetToProjectRoleDtoSet( userEntity.getRoles() ) );

        return projectUserDto;
    }

    protected ProjectTaskDto taskEntityToProjectTaskDto(TaskEntity taskEntity) {
        if ( taskEntity == null ) {
            return null;
        }

        ProjectTaskDto projectTaskDto = new ProjectTaskDto();

        projectTaskDto.setId( taskEntity.getId() );
        projectTaskDto.setName( taskEntity.getName() );
        projectTaskDto.setDescription( taskEntity.getDescription() );
        projectTaskDto.setStatus( taskEntity.getStatus() );

        return projectTaskDto;
    }

    protected List<ProjectTaskDto> taskEntityListToProjectTaskDtoList(List<TaskEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<ProjectTaskDto> list1 = new ArrayList<ProjectTaskDto>( list.size() );
        for ( TaskEntity taskEntity : list ) {
            list1.add( taskEntityToProjectTaskDto( taskEntity ) );
        }

        return list1;
    }

    protected Set<ProjectUserDto> userEntitySetToProjectUserDtoSet(Set<UserEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<ProjectUserDto> set1 = new LinkedHashSet<ProjectUserDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UserEntity userEntity : set ) {
            set1.add( userEntityToProjectUserDto( userEntity ) );
        }

        return set1;
    }

    protected ProjectResponseDto projectEntityToProjectResponseDto(ProjectEntity projectEntity) {
        if ( projectEntity == null ) {
            return null;
        }

        ProjectResponseDto projectResponseDto = new ProjectResponseDto();

        projectResponseDto.setId( projectEntity.getId() );
        projectResponseDto.setName( projectEntity.getName() );
        projectResponseDto.setOwner( userEntityToProjectUserDto( projectEntity.getOwner() ) );
        projectResponseDto.setDescription( projectEntity.getDescription() );
        projectResponseDto.setTasks( taskEntityListToProjectTaskDtoList( projectEntity.getTasks() ) );
        projectResponseDto.setUsers( userEntitySetToProjectUserDtoSet( projectEntity.getUsers() ) );

        return projectResponseDto;
    }

    protected Set<ProjectResponseDto> projectEntitySetToProjectResponseDtoSet(Set<ProjectEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<ProjectResponseDto> set1 = new LinkedHashSet<ProjectResponseDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ProjectEntity projectEntity : set ) {
            set1.add( projectEntityToProjectResponseDto( projectEntity ) );
        }

        return set1;
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

    protected TaskResponseDto taskEntityToTaskResponseDto(TaskEntity taskEntity) {
        if ( taskEntity == null ) {
            return null;
        }

        TaskResponseDto taskResponseDto = new TaskResponseDto();

        taskResponseDto.setId( taskEntity.getId() );
        taskResponseDto.setName( taskEntity.getName() );
        taskResponseDto.setDescription( taskEntity.getDescription() );
        taskResponseDto.setProject( projectEntityToTaskProjectDto( taskEntity.getProject() ) );
        taskResponseDto.setStatus( taskEntity.getStatus() );
        taskResponseDto.setUsers( userEntitySetToTaskUserDtoSet( taskEntity.getUsers() ) );

        return taskResponseDto;
    }

    protected Set<TaskResponseDto> taskEntitySetToTaskResponseDtoSet(Set<TaskEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<TaskResponseDto> set1 = new LinkedHashSet<TaskResponseDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TaskEntity taskEntity : set ) {
            set1.add( taskEntityToTaskResponseDto( taskEntity ) );
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

    protected RoleEntity projectRoleDtoToRoleEntity(ProjectRoleDto projectRoleDto) {
        if ( projectRoleDto == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId( projectRoleDto.getId() );
        roleEntity.setName( projectRoleDto.getName() );

        return roleEntity;
    }

    protected Set<RoleEntity> projectRoleDtoSetToRoleEntitySet(Set<ProjectRoleDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleEntity> set1 = new LinkedHashSet<RoleEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ProjectRoleDto projectRoleDto : set ) {
            set1.add( projectRoleDtoToRoleEntity( projectRoleDto ) );
        }

        return set1;
    }

    protected UserEntity projectUserDtoToUserEntity(ProjectUserDto projectUserDto) {
        if ( projectUserDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( projectUserDto.getId() );
        userEntity.setUsername( projectUserDto.getUsername() );
        userEntity.setEmail( projectUserDto.getEmail() );
        userEntity.setRoles( projectRoleDtoSetToRoleEntitySet( projectUserDto.getRoles() ) );

        return userEntity;
    }

    protected TaskEntity projectTaskDtoToTaskEntity(ProjectTaskDto projectTaskDto) {
        if ( projectTaskDto == null ) {
            return null;
        }

        TaskEntity taskEntity = new TaskEntity();

        if ( projectTaskDto.getId() != null ) {
            taskEntity.setId( projectTaskDto.getId() );
        }
        taskEntity.setName( projectTaskDto.getName() );
        taskEntity.setDescription( projectTaskDto.getDescription() );
        taskEntity.setStatus( projectTaskDto.getStatus() );

        return taskEntity;
    }

    protected List<TaskEntity> projectTaskDtoListToTaskEntityList(List<ProjectTaskDto> list) {
        if ( list == null ) {
            return null;
        }

        List<TaskEntity> list1 = new ArrayList<TaskEntity>( list.size() );
        for ( ProjectTaskDto projectTaskDto : list ) {
            list1.add( projectTaskDtoToTaskEntity( projectTaskDto ) );
        }

        return list1;
    }

    protected Set<UserEntity> projectUserDtoSetToUserEntitySet(Set<ProjectUserDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserEntity> set1 = new LinkedHashSet<UserEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ProjectUserDto projectUserDto : set ) {
            set1.add( projectUserDtoToUserEntity( projectUserDto ) );
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
        projectEntity.setOwner( projectUserDtoToUserEntity( projectResponseDto.getOwner() ) );
        projectEntity.setName( projectResponseDto.getName() );
        projectEntity.setDescription( projectResponseDto.getDescription() );
        projectEntity.setTasks( projectTaskDtoListToTaskEntityList( projectResponseDto.getTasks() ) );
        projectEntity.setUsers( projectUserDtoSetToUserEntitySet( projectResponseDto.getUsers() ) );

        return projectEntity;
    }

    protected Set<ProjectEntity> projectResponseDtoSetToProjectEntitySet(Set<ProjectResponseDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<ProjectEntity> set1 = new LinkedHashSet<ProjectEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ProjectResponseDto projectResponseDto : set ) {
            set1.add( projectResponseDtoToProjectEntity( projectResponseDto ) );
        }

        return set1;
    }

    protected ProjectEntity taskProjectDtoToProjectEntity(TaskProjectDto taskProjectDto) {
        if ( taskProjectDto == null ) {
            return null;
        }

        ProjectEntity projectEntity = new ProjectEntity();

        projectEntity.setId( taskProjectDto.getId() );
        projectEntity.setName( taskProjectDto.getName() );

        return projectEntity;
    }

    protected UserEntity taskUserDtoToUserEntity(TaskUserDto taskUserDto) {
        if ( taskUserDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( taskUserDto.getId() );
        userEntity.setUsername( taskUserDto.getUsername() );
        userEntity.setEmail( taskUserDto.getEmail() );
        userEntity.setRoles( roleDtoSetToRoleEntitySet( taskUserDto.getRoles() ) );

        return userEntity;
    }

    protected Set<UserEntity> taskUserDtoSetToUserEntitySet(Set<TaskUserDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserEntity> set1 = new LinkedHashSet<UserEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TaskUserDto taskUserDto : set ) {
            set1.add( taskUserDtoToUserEntity( taskUserDto ) );
        }

        return set1;
    }

    protected TaskEntity taskResponseDtoToTaskEntity(TaskResponseDto taskResponseDto) {
        if ( taskResponseDto == null ) {
            return null;
        }

        TaskEntity taskEntity = new TaskEntity();

        if ( taskResponseDto.getId() != null ) {
            taskEntity.setId( taskResponseDto.getId() );
        }
        taskEntity.setName( taskResponseDto.getName() );
        taskEntity.setDescription( taskResponseDto.getDescription() );
        taskEntity.setStatus( taskResponseDto.getStatus() );
        taskEntity.setProject( taskProjectDtoToProjectEntity( taskResponseDto.getProject() ) );
        taskEntity.setUsers( taskUserDtoSetToUserEntitySet( taskResponseDto.getUsers() ) );

        return taskEntity;
    }

    protected Set<TaskEntity> taskResponseDtoSetToTaskEntitySet(Set<TaskResponseDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<TaskEntity> set1 = new LinkedHashSet<TaskEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TaskResponseDto taskResponseDto : set ) {
            set1.add( taskResponseDtoToTaskEntity( taskResponseDto ) );
        }

        return set1;
    }
}
