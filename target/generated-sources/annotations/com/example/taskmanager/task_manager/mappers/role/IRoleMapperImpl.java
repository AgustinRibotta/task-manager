package com.example.taskmanager.task_manager.mappers.role;

import com.example.taskmanager.task_manager.dtos.permission.PermissionDto;
import com.example.taskmanager.task_manager.dtos.role.RoleRequestDto;
import com.example.taskmanager.task_manager.dtos.role.RoleResponseDto;
import com.example.taskmanager.task_manager.entities.PermissionEntity;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import com.example.taskmanager.task_manager.mappers.IPermissionDto;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-14T22:01:00+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Arch Linux)"
)
@Component
public class IRoleMapperImpl implements IRoleMapper {

    @Autowired
    private IPermissionDto iPermissionDto;

    @Override
    public RoleEntity toEntity(RoleRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId( dto.getId() );
        roleEntity.setName( dto.getName() );

        return roleEntity;
    }

    @Override
    public RoleResponseDto toDto(RoleEntity entity) {
        if ( entity == null ) {
            return null;
        }

        RoleResponseDto roleResponseDto = new RoleResponseDto();

        roleResponseDto.setPermissions( permissionEntitySetToPermissionDtoSet( entity.getPermissions() ) );
        roleResponseDto.setId( entity.getId() );
        roleResponseDto.setName( entity.getName() );

        return roleResponseDto;
    }

    protected Set<PermissionDto> permissionEntitySetToPermissionDtoSet(Set<PermissionEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<PermissionDto> set1 = new LinkedHashSet<PermissionDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PermissionEntity permissionEntity : set ) {
            set1.add( iPermissionDto.toPermissionDto( permissionEntity ) );
        }

        return set1;
    }
}
