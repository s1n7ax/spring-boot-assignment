package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.BookMapper;
import com.example.demo.record.BookRecord;
import com.example.demo.repository.BookRepository;

import jakarta.transaction.Transactional;

@Service
public class BookServiceImpl implements BookService {
  @Autowired
  private BookRepository bookRepository;

  @Transactional
  public BookRecord createOrUpdateBook(String isbn, BookRecord bookRecord) {
    var mapper = new BookMapper();
    var optionalBook = bookRepository.findByIsbn(isbn);

    if (optionalBook.isEmpty()) {
      var newBook = bookRepository.save(mapper.toBook(bookRecord));
      newBook.setAvailableQuantity(1);
      return mapper.toBookRecord(newBook);
    }

    var existingBook = optionalBook.get();

    existingBook.setTitle(bookRecord.title());
    existingBook.setAuthor(bookRecord.author());
    existingBook.setAvailableQuantity(existingBook.getAvailableQuantity() + 1);

    var newBook = bookRepository.save(existingBook);
    return mapper.toBookRecord(newBook);
  }

  @Transactional
  public Page<BookRecord> getAllBooks(int pageNumber, int pageSize) {
    var mapper = new BookMapper();
    var page = PageRequest.of(pageNumber, pageSize);
    var results = bookRepository.findAll(page);
    return results.map(mapper::toBookRecord);
  }
}
