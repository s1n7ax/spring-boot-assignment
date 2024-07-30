package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.record.BookRecord;
import com.example.demo.service.BookService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@RestController
public class BookController {
  @Autowired
  private BookService bookService;

  @PutMapping("/book/{isbn}")
  public BookRecord createBook(@PathVariable(required = true) @Size(max = 13, message = "something") String isbn,
      @RequestBody @Valid BookRecord book) {
    return bookService.createOrUpdateBook(isbn, book);
  }

  @GetMapping("/books")
  public Page<BookRecord> getAllBooks(@RequestParam(defaultValue = "0") @Min(value = 0) Integer pageNumber,
      @RequestParam(defaultValue = "20") @Min(value = 1) @Max(value = 20) Integer pageSize) {
    return bookService.getAllBooks(pageNumber, pageSize);
  }

}
