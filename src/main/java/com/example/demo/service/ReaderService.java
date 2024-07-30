package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Reader;

@Service
public interface ReaderService {

  Reader createReader(Reader reader);
}
