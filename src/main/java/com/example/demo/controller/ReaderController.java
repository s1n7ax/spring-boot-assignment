package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Reader;
import com.example.demo.service.ReaderService;

import jakarta.validation.Valid;

@RestController
public class ReaderController {
  @Autowired
  private ReaderService userService;

  @PostMapping("/reader")
  public Reader createReader(@RequestBody @Valid Reader user) {
    return userService.createReader(user);
  }

}
