package com.vspiewak.stamped.domain;

import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class UserService implements UserServiceApi {

  private final UserStorePort userStorePort;

  @Override
  public Optional<User> getUserById(UUID id) {
    return userStorePort.findById(id);
  }
}
