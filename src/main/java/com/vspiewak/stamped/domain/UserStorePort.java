package com.vspiewak.stamped.domain;

import java.util.Optional;
import java.util.UUID;

public interface UserStorePort {

  Optional<User> findById(UUID id);
}
