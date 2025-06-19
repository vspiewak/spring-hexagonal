package com.vspiewak.stamped;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

import com.vspiewak.stamped.infrastructure.db.entity.UserEntity;
import com.vspiewak.stamped.infrastructure.db.repository.UserRepository;
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

    when()
        .get("/users/{id}", uuid)
        .then()
        .statusCode(200)
        .body("username", equalTo(user.getUsername()));
  }
}
