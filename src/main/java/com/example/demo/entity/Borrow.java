package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = { "reader_id", "book_id" }),
})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Borrow {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bookId")
  @NotNull
  private Book book;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "readerId")
  @NotNull
  private Reader reader;
}
