# Stamped

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

### 🎨 format code

    ./mvnw spotless:apply

### 🚀 run 
    
    ./mvnw clean sprint-boot:run

### ⚡️ run with samples data
  
    ./mvnw clean spring-boot:run -Dspring-boot.run.profiles=with-samples

### 🧪 test

    ./mvnw clean test

### 📊 jacoco report
    
    ./mvnw clean jacoco:report
