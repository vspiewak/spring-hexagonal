package com.vspiewak.stamped.infrastructure.db;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.vspiewak.stamped.domain.User;
import com.vspiewak.stamped.domain.UserStorePort;
import com.vspiewak.stamped.infrastructure.db.mapper.UserMapper;
import com.vspiewak.stamped.infrastructure.db.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserStoreAdapter implements UserStorePort {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public Optional<User> findById(UUID id) {
    return userRepository.findById(id).map(userMapper::toDomain);
  }
}
