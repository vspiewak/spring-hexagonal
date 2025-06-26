package com.vspiewak.stamped.application.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.vspiewak.stamped.application.mapper.UserResponseMapper;
import com.vspiewak.stamped.contract.api.UserApi;
import com.vspiewak.stamped.contract.model.UserResponse;
import com.vspiewak.stamped.domain.usecase.GetUserUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserResponseMapper userResponseMapper;
    private final GetUserUseCase getUserUseCase;

    @Override
    public ResponseEntity<UserResponse> getUserById(UUID id) {
        return ResponseEntity.ok(userResponseMapper.toResponse(getUserUseCase.getUser(id)));
    }
}
