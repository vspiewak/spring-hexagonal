package com.vspiewak.stamped;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.vspiewak.stamped.infrastructure.db.entity.UserEntity;
import com.vspiewak.stamped.infrastructure.db.repository.UserRepository;
import com.vspiewak.stamped.runtime.StampedApplication;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(classes = StampedApplication.class)
@EnableJpaRepositories(basePackageClasses = StampedApplication.class)
class StampedApplicationTest {

  @Autowired private UserRepository userRepository;

  @AfterEach
  void afterEach() {
    userRepository.deleteAll();
  }

  @Test
  void contextLoads(ApplicationContext context) {
    assertThat(context).isNotNull();
  }

  @Test
  void jpaLoad() {
    assertThat(userRepository).isNotNull();
  }

  @Test
  void canFindUserById() {

    var expected = new UserEntity();
    expected.setUsername("test");

    var saved = userRepository.save(expected);
    assertThat(saved).isNotNull();

    var actual = userRepository.findById(saved.getId());
    assertThat(actual)
        .isPresent()
        .get()
        .hasFieldOrPropertyWithValue("username", expected.getUsername());
  }
}
