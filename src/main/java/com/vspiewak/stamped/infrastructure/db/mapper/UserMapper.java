package com.vspiewak.stamped.infrastructure.db.mapper;

import org.mapstruct.Mapper;

import com.vspiewak.stamped.domain.User;
import com.vspiewak.stamped.infrastructure.db.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

  User toDomain(UserEntity source);
}
