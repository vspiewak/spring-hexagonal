package com.vspiewak.hexagonal.unit;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.vspiewak.hexagonal.domain.exception.UserNotFoundException;
import com.vspiewak.hexagonal.domain.model.User;
import com.vspiewak.hexagonal.domain.service.UserService;

public class UserServiceTest {

    @Test
    public void should_return_user_not_found() {

        // given
        var s = new UserService(_ -> Optional.empty());

        assertThatThrownBy(
                        // when
                        () -> {
                            s.getUser(UUID.randomUUID());
                        })
                // then
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    public void should_return_user_found() {

        // given
        var u = new User(UUID.randomUUID(), "username");
        var s = new UserService(_ -> Optional.of(u));

        // when
        var actual = s.getUser(UUID.randomUUID());

        // then
        assertThat(actual).isEqualTo(u);
    }
}
