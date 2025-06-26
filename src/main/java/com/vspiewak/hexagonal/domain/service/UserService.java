package com.vspiewak.hexagonal.domain.service;

import java.util.UUID;

import com.vspiewak.hexagonal.domain.annotation.DomainService;
import com.vspiewak.hexagonal.domain.exception.UserNotFoundException;
import com.vspiewak.hexagonal.domain.model.User;
import com.vspiewak.hexagonal.domain.port.GetUserPort;
import com.vspiewak.hexagonal.domain.usecase.GetUserUseCase;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class UserService implements GetUserUseCase {

    private final GetUserPort getUserPort;

    @Override
    public User getUser(UUID id) {
        return getUserPort.getUserById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
