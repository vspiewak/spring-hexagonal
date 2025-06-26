package com.vspiewak.stamped.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.vspiewak.stamped.domain.model.User;
import com.vspiewak.stamped.infrastructure.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(UserEntity source);
}
