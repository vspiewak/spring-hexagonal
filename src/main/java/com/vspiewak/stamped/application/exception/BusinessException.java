package com.vspiewak.stamped.application.exception;

public class BusinessException extends RuntimeException {
  BusinessException(String message) {
    super(message);
  }
}
