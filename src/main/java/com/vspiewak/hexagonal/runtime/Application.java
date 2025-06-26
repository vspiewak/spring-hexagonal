package com.vspiewak.hexagonal.runtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.vspiewak.hexagonal.domain.annotation.DomainService;

@SpringBootApplication
@ComponentScan(
        basePackages = "com.vspiewak",
        includeFilters =
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = {DomainService.class}))
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
