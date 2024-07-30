package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Borrow;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
  void deleteByReaderIdAndBookId(long readerId, long bookId);
}
