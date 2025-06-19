package com.vspiewak.stamped.domain;

import java.util.Optional;
import java.util.UUID;

public interface UserServiceApi {

  Optional<User> getUserById(UUID id);
}
