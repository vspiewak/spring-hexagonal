package com.vspiewak.hexagonal.integration;

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

import com.vspiewak.hexagonal.config.TestcontainersConfiguration;
import com.vspiewak.hexagonal.infrastructure.entity.UserEntity;
import com.vspiewak.hexagonal.infrastructure.repository.UserRepository;
import com.vspiewak.hexagonal.runtime.Application;

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
    void should_return_user_by_id() {

        var user = new UserEntity();
        user.setUsername("username");

        var saved = userRepository.saveAndFlush(user);
        var uuid = saved.getId();

        when().get("/users/{id}", uuid)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(uuid.toString()))
                .body("username", equalTo(user.getUsername()));
    }

    @Test
    void should_return_user_not_found() {

        var uuid = UUID.randomUUID();

        when().get("/users/{id}", uuid)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("type", equalTo("user"))
                .body("status", equalTo(HttpStatus.NOT_FOUND.value()));
    }
}
