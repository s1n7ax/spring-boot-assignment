package com.example.demo.mapper;

import java.util.Arrays;

import com.example.demo.entity.Book;
import com.example.demo.record.BookRecord;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class BookMapper {

  public Book toBook(BookRecord bookRecord) {
    return Book
        .builder()
        .isbn(bookRecord.isbn())
        .title(bookRecord.title())
        .author(bookRecord.author())
        .availableQuantity(0)
        .build();
  }

  public BookRecord toBookRecord(Book book) {
    return new BookRecord(
        book.getId(),
        book.getIsbn(),
        book.getTitle(),
        book.getAuthor());
  }

  public BookRecord[] toBookRecord(Book[] books) {
    return (BookRecord[]) Arrays.stream(books)
        .map(this::toBookRecord)
        .toArray();
  }
}
