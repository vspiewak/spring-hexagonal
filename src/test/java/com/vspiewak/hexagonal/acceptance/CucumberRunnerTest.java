package com.vspiewak.hexagonal.acceptance;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.vspiewak.hexagonal.config.TestcontainersConfiguration;
import com.vspiewak.hexagonal.runtime.Application;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", stepNotifications = true)
@CucumberContextConfiguration
@Import(TestcontainersConfiguration.class)
@SpringBootTest(
        classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberRunnerTest {}
