package com.vspiewak.stamped.domain.usecase;

import java.util.UUID;

import com.vspiewak.stamped.domain.model.User;

@FunctionalInterface
public interface GetUserUseCase {

    User getUser(UUID id);
}
