package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.record.BookRecord;

@Service
public interface BookService {
  public BookRecord createOrUpdateBook(String isbn, BookRecord book);

  Page<BookRecord> getAllBooks(int pageNumber, int pageSize);
}
