package com.vspiewak.stamped.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import com.vspiewak.stamped.config.TestcontainersConfiguration;
import com.vspiewak.stamped.infrastructure.entity.UserEntity;
import com.vspiewak.stamped.infrastructure.repository.UserRepository;
import com.vspiewak.stamped.runtime.Application;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(classes = Application.class)
class ApplicationTest {

    @Autowired private UserRepository userRepository;

    @AfterEach
    void afterEach() {
        userRepository.deleteAll();
    }

    @Test
    void shouldLoadContext(ApplicationContext context) {
        assertThat(context).isNotNull();
    }

    @Test
    void shouldLoadJpa() {
        assertThat(userRepository).isNotNull();
    }

    @Test
    void shouldFindById() {

        var user = new UserEntity();
        user.setUsername("test");

        var saved = userRepository.save(user);
        assertThat(saved).isNotNull();

        var actual = userRepository.findById(saved.getId());
        assertThat(actual)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("username", user.getUsername());
    }
}
