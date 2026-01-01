package com.example.booking.interfaces;

import org.mapstruct.Mapper;

import com.example.booking.domain.models.User;
import com.example.booking.infrastructure.persistence.UserEntity;


@Mapper(componentModel = "spring")
public interface UserMapper {
    
    User toDomain(UserEntity userEntity);
    UserEntity toEntity(User user);
}
