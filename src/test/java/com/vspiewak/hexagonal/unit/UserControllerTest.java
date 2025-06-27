package com.vspiewak.hexagonal.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.vspiewak.hexagonal.application.controller.UserController;
import com.vspiewak.hexagonal.application.mapper.UserResponseMapper;
import com.vspiewak.hexagonal.contract.model.UserResponse;
import com.vspiewak.hexagonal.domain.exception.UserNotFoundException;
import com.vspiewak.hexagonal.domain.model.User;
import com.vspiewak.hexagonal.domain.usecase.GetUserUseCase;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock private GetUserUseCase getUserUseCase;
    @Mock private UserResponseMapper userResponseMapper;

    @InjectMocks private UserController controller;

    @Test
    public void should_return_user_not_found_exception() {

        // given
        var uuid = UUID.randomUUID();

        // when
        when(getUserUseCase.getUser(uuid)).thenThrow(new UserNotFoundException(uuid));

        assertThatThrownBy(
                        // when
                        () -> {
                            controller.getUserById(uuid);
                        })
                // then
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    public void should_return_user() {

        // given
        var uuid = UUID.randomUUID();
        var user = new User(uuid, "username");
        var userResponse = new UserResponse().id(user.id()).username(user.username());

        // when
        when(getUserUseCase.getUser(uuid)).thenReturn(user);
        when(userResponseMapper.toResponse(user)).thenReturn(userResponse);
        var response = controller.getUserById(uuid);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isEqualTo(user.id());
        assertThat(response.getBody().getUsername()).isEqualTo(user.username());
    }
}
