package com.vspiewak.hexagonal.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.vspiewak.hexagonal.domain.model.User;
import com.vspiewak.hexagonal.infrastructure.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(UserEntity source);
}
