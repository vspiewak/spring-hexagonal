package com.vspiewak.hexagonal.domain.usecase;

import java.util.UUID;

import com.vspiewak.hexagonal.domain.model.User;

@FunctionalInterface
public interface GetUserUseCase {

    User getUser(UUID id);
}
