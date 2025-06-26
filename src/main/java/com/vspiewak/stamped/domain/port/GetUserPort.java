package com.vspiewak.stamped.domain.port;

import java.util.Optional;
import java.util.UUID;

import com.vspiewak.stamped.domain.model.User;

@FunctionalInterface
public interface GetUserPort {

    Optional<User> getUserById(UUID id);
}
