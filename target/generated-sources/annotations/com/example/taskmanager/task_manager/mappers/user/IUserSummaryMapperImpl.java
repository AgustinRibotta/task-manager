package com.example.taskmanager.task_manager.mappers.user;

import com.example.taskmanager.task_manager.dtos.role.RoleRequestDto;
import com.example.taskmanager.task_manager.dtos.user.UserSummaryDto;
import com.example.taskmanager.task_manager.entities.RoleEntity;
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
public class IUserSummaryMapperImpl implements IUserSummaryMapper {

    @Override
    public UserSummaryDto toDto(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserSummaryDto userSummaryDto = new UserSummaryDto();

        userSummaryDto.setId( entity.getId() );
        userSummaryDto.setUsername( entity.getUsername() );
        userSummaryDto.setEmail( entity.getEmail() );
        userSummaryDto.setRoles( roleEntitySetToRoleRequestDtoSet( entity.getRoles() ) );

        return userSummaryDto;
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
}
