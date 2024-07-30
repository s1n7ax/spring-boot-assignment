package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Borrow;
import com.example.demo.service.BorrowService;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.server.PathParam;

@RestController
public class BorrowController {

  @Autowired
  private BorrowService borrowService;

  @PostMapping("/reader/{readerId}/book/{bookId}")
  public Borrow borrowBook(
      @NotNull @Min(1) @PathVariable Long readerId,
      @NotNull @Min(1) @PathVariable Long bookId) {
    return borrowService.borrowBook(readerId, bookId);
  }

  @DeleteMapping("/reader/{readerId}/book/{bookId}")
  public void returnBook(
      @NotNull @Min(1) @PathVariable("readerId") Long readerId,
      @NotNull @Min(1) @PathVariable("bookId") Long bookId) {
    borrowService.returnBook(readerId, bookId);
  }

}
