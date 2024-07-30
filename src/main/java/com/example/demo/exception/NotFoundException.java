package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource Not Found")
@NoArgsConstructor
public class NotFoundException extends RuntimeException {

  public NotFoundException(String message) {
    super(message);
  }
}
