package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Borrow;

@Service
public interface BorrowService {

  Borrow borrowBook(long readerId, long bookId);

  void returnBook(long readerId, long bookId);
}
