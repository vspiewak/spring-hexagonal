package com.vspiewak.stamped.application.mapper;

import org.mapstruct.Mapper;

import com.vspiewak.stamped.contract.model.UserResponse;
import com.vspiewak.stamped.domain.User;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

  UserResponse toResponse(User source);
}
