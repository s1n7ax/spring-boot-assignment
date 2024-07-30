package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.entity.Borrow;
import com.example.demo.entity.Reader;
import com.example.demo.exception.ConflictException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BorrowRepository;
import com.example.demo.repository.ReaderRepository;

import jakarta.transaction.Transactional;

@Service
public class BorrowServiceImpl implements BorrowService {
  @Autowired
  private ReaderRepository readerRepository;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private BorrowRepository borrowRepository;

  @Transactional
  public Borrow borrowBook(long userId, long bookId) {
    var reader = getReaderById(userId);
    var book = getBookById(bookId);

    if (book.getAvailableQuantity() < 1) {
      throw new NotFoundException("book::" + bookId + " is not avalable");
    }

    book.setAvailableQuantity(book.getAvailableQuantity() - 1);

    var borrow = new Borrow();
    borrow.setReader(reader);
    borrow.setBook(book);

    bookRepository.save(book);

    try {
      return borrowRepository.save(borrow);
    } catch (DataIntegrityViolationException ex) {
      throw new ConflictException("user::" + userId + " has already borrowed book " + book);
    }
  }

  @Transactional
  public void returnBook(long readerId, long bookId) {
    getReaderById(readerId);
    var book = getBookById(bookId);

    book.setAvailableQuantity(book.getAvailableQuantity() + 1);

    bookRepository.save(book);
    borrowRepository.deleteByReaderIdAndBookId(readerId, bookId);
  }

  private Reader getReaderById(long userId) {
    var reader = readerRepository.findById(userId);

    if (reader.isEmpty())
      throw new NotFoundException("user::" + userId + " does not exist");

    return reader.get();
  }

  private Book getBookById(long bookId) {
    var book = bookRepository.findById(bookId);

    if (book.isEmpty())
      throw new NotFoundException("book::" + bookId + " does not exist");

    return book.get();
  }
}
