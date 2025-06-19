package com.vspiewak.stamped.runtime.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.vspiewak")
@EntityScan(basePackages = "com.vspiewak")
public class JpaConfig {}
