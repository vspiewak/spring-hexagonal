package com.vspiewak.stamped.application.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.vspiewak.stamped.application.exception.UserNotFoundException;
import com.vspiewak.stamped.application.mapper.UserResponseMapper;
import com.vspiewak.stamped.contract.api.UserApi;
import com.vspiewak.stamped.contract.model.UserResponse;
import com.vspiewak.stamped.domain.UserServiceApi;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

  private final UserResponseMapper userResponseMapper;
  private final UserServiceApi userServiceApi;

  @Override
  public ResponseEntity<UserResponse> getUserById(UUID id) {
    return ResponseEntity.ok(
        userServiceApi
            .getUserById(id)
            .map(userResponseMapper::toResponse)
            .orElseThrow(() -> new UserNotFoundException(id)));
  }
}
