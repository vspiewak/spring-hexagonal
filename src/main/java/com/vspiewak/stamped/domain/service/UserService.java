package com.vspiewak.stamped.domain.service;

import java.util.UUID;

import com.vspiewak.stamped.domain.annotation.DomainService;
import com.vspiewak.stamped.domain.exception.UserNotFoundException;
import com.vspiewak.stamped.domain.model.User;
import com.vspiewak.stamped.domain.port.GetUserPort;
import com.vspiewak.stamped.domain.usecase.GetUserUseCase;

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
