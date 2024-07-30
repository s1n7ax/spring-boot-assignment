package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Reader;
import com.example.demo.exception.ConflictException;
import com.example.demo.repository.ReaderRepository;

@Service
public class ReaderServiceImpl implements ReaderService {
  @Autowired
  private ReaderRepository readerRepository;

  public Reader createReader(Reader reader) {
    var existingReader = readerRepository.findByEmail(reader.getEmail());

    if (existingReader.isEmpty()) {
      return readerRepository.save(reader);
    }

    throw new ConflictException("reader by email::" + reader.getEmail() + " already exists");
  }
}
