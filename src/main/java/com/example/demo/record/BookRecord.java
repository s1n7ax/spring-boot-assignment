package com.example.demo.record;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookRecord(
    @Min(value = 0, message = "Id should not be less than -1") Long id,
    @NotNull @Size(min = 10, max = 13, message = "ISBN should be between 10 and 13 characters") String isbn,
    @NotNull @Size(min = 1, max = 255, message = "Title should be between 10 and 13 characters") String title,
    @NotNull @Size(min = 1, max = 255, message = "Author should be between 10 and 13 characters") String author) {
}
