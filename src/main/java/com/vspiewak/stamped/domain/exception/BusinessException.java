package com.vspiewak.stamped.domain.exception;

public class BusinessException extends RuntimeException {

    BusinessException(String message) {
        super(message);
    }
}
