package com.vspiewak.stamped.application.exception;

import java.util.UUID;

public class UserNotFoundException extends BusinessException {
  public UserNotFoundException(UUID id) {
    super(String.format("User with id %s not found", id));
  }
}
