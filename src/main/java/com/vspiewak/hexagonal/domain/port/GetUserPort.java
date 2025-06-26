package com.vspiewak.hexagonal.domain.port;

import java.util.Optional;
import java.util.UUID;

import com.vspiewak.hexagonal.domain.model.User;

@FunctionalInterface
public interface GetUserPort {

    Optional<User> getUserById(UUID id);
}
