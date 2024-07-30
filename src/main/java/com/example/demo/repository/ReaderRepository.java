package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Reader;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
  Optional<Reader> findByEmail(String email);
}
