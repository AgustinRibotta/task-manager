package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.UserSummaryDto;
import com.example.taskmanager.task_manager.entities.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-17T11:42:22+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Ubuntu)"
)
@Component
public class IUserForProjectMapperImpl implements IUserForProjectMapper {

    @Override
    public UserSummaryDto userEntityToUserDtoForProjectDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserSummaryDto userSummaryDto = new UserSummaryDto();

        userSummaryDto.setId( userEntity.getId() );
        userSummaryDto.setUsername( userEntity.getUsername() );

        return userSummaryDto;
    }

    @Override
    public UserEntity userDtoForProjectDtoToUserEntity(UserSummaryDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( dto.getId() );
        userEntity.setUsername( dto.getUsername() );

        return userEntity;
    }
}
