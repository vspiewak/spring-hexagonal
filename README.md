# Spring Hexagonal Architecture

A Hexagonal Architecture REST API Boilerplate built with Spring Boot 3

## ✨ Features

* 🤝 Contract first API
* 🌐 Spring MVC
* 🩺 Actuator
* 🗄️ Data JPA
* 🧬 Liquibase
* 🏗️ ArchUnit
* 🧪 Rest Assured
* 🐳 TestContainers
* 🥒 Cucumber
* 📊 Jacoco coverage
* 🎨 Spotless 
* 🪝 Git pre-commit hook


## 🛠️ Commands

### 🎨 Format code

    ./mvnw spotless:apply

### 🚀 Run 
    
    ./mvnw clean sprint-boot:run

### ⚡️ Run with samples data
  
    ./mvnw clean spring-boot:run -Dspring-boot.run.profiles=with-samples

### 🧪 Launch Tests

    ./mvnw clean test

### 📊 Jacoco report
    
    ./mvnw clean jacoco:report
