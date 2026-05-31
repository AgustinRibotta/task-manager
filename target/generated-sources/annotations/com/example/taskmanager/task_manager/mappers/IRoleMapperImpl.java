package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.RoleDto;
import com.example.taskmanager.task_manager.dtos.project.PermissionDto;
import com.example.taskmanager.task_manager.entities.PermissionEntity;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-31T12:22:59+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Arch Linux)"
)
@Component
public class IRoleMapperImpl implements IRoleMapper {

    @Override
    public RoleDto roleEntityToRoleDto(RoleEntity entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( entity.getId() );
        roleDto.setName( entity.getName() );
        roleDto.setPermissions( permissionEntitySetToPermissionDtoSet( entity.getPermissions() ) );

        return roleDto;
    }

    @Override
    public RoleEntity roleDtoToRoleEntity(RoleDto dto) {
        if ( dto == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId( dto.getId() );
        roleEntity.setName( dto.getName() );
        roleEntity.setPermissions( permissionDtoSetToPermissionEntitySet( dto.getPermissions() ) );

        return roleEntity;
    }

    protected PermissionDto permissionEntityToPermissionDto(PermissionEntity permissionEntity) {
        if ( permissionEntity == null ) {
            return null;
        }

        PermissionDto permissionDto = new PermissionDto();

        permissionDto.setId( permissionEntity.getId() );
        permissionDto.setName( permissionEntity.getName() );

        return permissionDto;
    }

    protected Set<PermissionDto> permissionEntitySetToPermissionDtoSet(Set<PermissionEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<PermissionDto> set1 = new LinkedHashSet<PermissionDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PermissionEntity permissionEntity : set ) {
            set1.add( permissionEntityToPermissionDto( permissionEntity ) );
        }

        return set1;
    }

    protected PermissionEntity permissionDtoToPermissionEntity(PermissionDto permissionDto) {
        if ( permissionDto == null ) {
            return null;
        }

        PermissionEntity permissionEntity = new PermissionEntity();

        if ( permissionDto.getId() != null ) {
            permissionEntity.setId( permissionDto.getId() );
        }
        permissionEntity.setName( permissionDto.getName() );

        return permissionEntity;
    }

    protected Set<PermissionEntity> permissionDtoSetToPermissionEntitySet(Set<PermissionDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<PermissionEntity> set1 = new LinkedHashSet<PermissionEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PermissionDto permissionDto : set ) {
            set1.add( permissionDtoToPermissionEntity( permissionDto ) );
        }

        return set1;
    }
}
