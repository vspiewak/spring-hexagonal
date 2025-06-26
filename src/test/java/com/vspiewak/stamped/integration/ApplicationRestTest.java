package com.vspiewak.stamped.integration;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;

import com.vspiewak.stamped.config.TestcontainersConfiguration;
import com.vspiewak.stamped.infrastructure.entity.UserEntity;
import com.vspiewak.stamped.infrastructure.repository.UserRepository;
import com.vspiewak.stamped.runtime.Application;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(
        classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationRestTest {

    @LocalServerPort private Integer port;

    @Autowired private UserRepository userRepository;

    @BeforeEach
    void beforeEach() {
        RestAssured.port = port;
        userRepository.deleteAll();
    }

    @Test
    void shouldReturnUserById() {

        var user = new UserEntity();
        user.setUsername("username");

        var saved = userRepository.saveAndFlush(user);
        var uuid = saved.getId();

        when().get("/users/{id}", uuid)
                .then()
                .statusCode(200)
                .body("username", equalTo(user.getUsername()));
    }

    @Test
    void shouldReturnUserNotFound() {

        var uuid = UUID.randomUUID();

        when().get("/users/{id}", uuid)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("type", equalTo("user"))
                .body("status", equalTo(HttpStatus.NOT_FOUND.value()));
    }
}
