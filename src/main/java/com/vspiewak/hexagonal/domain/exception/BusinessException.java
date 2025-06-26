package com.vspiewak.hexagonal.domain.exception;

public class BusinessException extends RuntimeException {

    BusinessException(String message) {
        super(message);
    }
}
