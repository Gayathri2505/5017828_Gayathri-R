package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.exception.BookNotFoundException;
import com.example.bookstoreapi.mapper.BookMapper;
import com.example.bookstoreapi.model.Book;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();
    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(books.stream().map(bookMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id) {
        Book book = findBookById(id).orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
        return ResponseEntity.ok(bookMapper.toDTO(book));
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        if (bookDTO.getId() == null || bookDTO.getId() <= 0) {
            bookDTO.setId((long) (books.size() + 1)); // Set a new ID if not provided or invalid
        }
        Book book = bookMapper.toEntity(bookDTO);
        books.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookMapper.toDTO(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@Valid @PathVariable Integer id, @Valid @RequestBody BookDTO bookDTO) {
        Book book = findBookById(id).orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        book.setIsbn(bookDTO.getIsbn());
        return ResponseEntity.ok(bookMapper.toDTO(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        Book book = findBookById(id).orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
        books.remove(book);
        return ResponseEntity.noContent().build();
    }

    private Optional<Book> findBookById(Integer id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst();
    }
}
