package com.example.demo.util.data;

import com.example.demo.entity.Reader;
import com.github.javafaker.Faker;

public class ReaderData {
  static Faker faker = new Faker();

  static public Reader getReader() {
    return Reader
        .builder()
        .name(faker.name().name())
        .email(faker.internet().emailAddress())
        .build();
  }
}
