package com.vspiewak.hexagonal.application.mapper;

import org.mapstruct.Mapper;

import com.vspiewak.hexagonal.contract.model.UserResponse;
import com.vspiewak.hexagonal.domain.model.User;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    UserResponse toResponse(User source);
}
