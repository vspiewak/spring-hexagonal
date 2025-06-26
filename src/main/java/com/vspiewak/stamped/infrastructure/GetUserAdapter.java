package com.vspiewak.stamped.infrastructure;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.vspiewak.stamped.domain.model.User;
import com.vspiewak.stamped.domain.port.GetUserPort;
import com.vspiewak.stamped.infrastructure.mapper.UserMapper;
import com.vspiewak.stamped.infrastructure.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetUserAdapter implements GetUserPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id).map(userMapper::toDomain);
    }
}
