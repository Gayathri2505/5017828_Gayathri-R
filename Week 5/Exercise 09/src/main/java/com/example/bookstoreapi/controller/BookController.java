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

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();
    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> bookDTOs = books.stream()
                                      .map(bookMapper::toDTO)
                                      .collect(Collectors.toList());

        // Add HATEOAS links to each bookDTO
        bookDTOs.forEach(bookDTO -> {
            int id = bookDTO.getId();
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(id)).withSelfRel();
            bookDTO.add(selfLink);
        });

        return ResponseEntity.ok(bookDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id) {
        Book book = findBookById(id).orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));

        BookDTO bookDTO = bookMapper.toDTO(book);

        // Add self link
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(id)).withSelfRel();
        bookDTO.add(selfLink);

        // Add link to all books
        Link allBooksLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withRel("all-books");
        bookDTO.add(allBooksLink);

        return ResponseEntity.ok(bookDTO);
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        if (bookDTO.getId() == null || bookDTO.getId() <= 0) {
            bookDTO.setId((books.size() + 1)); // Set a new ID if not provided or invalid
        }
        Book book = bookMapper.toEntity(bookDTO);
        books.add(book);

        BookDTO createdBookDTO = bookMapper.toDTO(book);

        // Add self link
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(createdBookDTO.getId())).withSelfRel();
        createdBookDTO.add(selfLink);

        // Add link to all books
        Link allBooksLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withRel("all-books");
        createdBookDTO.add(allBooksLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdBookDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@Valid @PathVariable Integer id, @Valid @RequestBody BookDTO bookDTO) {
        Book book = findBookById(id).orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        book.setIsbn(bookDTO.getIsbn());

        BookDTO updatedBookDTO = bookMapper.toDTO(book);

        // Add self link
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(id)).withSelfRel();
        updatedBookDTO.add(selfLink);

        // Add link to all books
        Link allBooksLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withRel("all-books");
        updatedBookDTO.add(allBooksLink);

        return ResponseEntity.ok(updatedBookDTO);
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
