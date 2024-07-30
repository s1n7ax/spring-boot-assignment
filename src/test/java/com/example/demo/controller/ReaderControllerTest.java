package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.repository.ReaderRepository;
import com.example.demo.util.data.ReaderData;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.NONE)
@AutoConfigureMockMvc
public class ReaderControllerTest {
  @MockBean
  private ReaderRepository readerRepository;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void createReaderTest() throws Exception {
    var reader = ReaderData.getReader();

    when(readerRepository.findByEmail(anyString())).thenReturn(Optional.empty());
    when(readerRepository.save(null)).thenReturn(reader);

    var json = """
        { "name": "%s", "email": "%s" }
        """.formatted(reader.getName(), reader.getEmail());

    mockMvc
        .perform(
            post("/reader").contentType("application/json").content(json))
        .andExpect(status().isOk());
  }

  @Test
  void createReader_RequestDataValidationTest() throws Exception {
    String[] requests = {
        """
            { "name": "%s", "email": "%s" }
            """.formatted("", "user@email.com"),

        """
            { "name": "%s", "email": "%s" }
            """.formatted("user", "user"),

        """
            { "name": "%s", "email": "%s" }
            """.formatted("", "user")
    };

    for (String payload : requests) {
      mockMvc
          .perform(
              post("/reader").contentType("application/json").content(payload))
          .andExpect(status().isBadRequest());
    }
  }

  @Test
  void createReader_DuplicateReaderValidationTest() throws Exception {
    var reader = ReaderData.getReader();

    when(readerRepository.findByEmail(anyString())).thenReturn(Optional.empty());
    when(readerRepository.save(null)).thenReturn(reader);

    var json = """
        { "name": "%s", "email": "%s" }
        """.formatted(reader.getName(), reader.getEmail());

    mockMvc
        .perform(
            post("/reader").contentType("application/json").content(json))
        .andExpect(status().isOk());

    when(readerRepository.findByEmail(anyString())).thenReturn(Optional.of(reader));
    when(readerRepository.save(null)).thenThrow(DataIntegrityViolationException.class);

    mockMvc
        .perform(
            post("/reader").contentType("application/json").content(json))
        .andExpect(status().isConflict());
  }
}
