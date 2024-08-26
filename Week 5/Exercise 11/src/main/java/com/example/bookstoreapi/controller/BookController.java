package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.exception.BookNotFoundException;
import com.example.bookstoreapi.mapper.BookMapper;
import com.example.bookstoreapi.model.Book;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    private final MeterRegistry meterRegistry;

    // Constructor with MeterRegistry injection
    public BookController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> bookDTOs = books.stream()
                                      .map(bookMapper::toDTO)
                                      .collect(Collectors.toList());

        // Add HATEOAS links
        bookDTOs.forEach(bookDTO -> {
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(bookDTO.getId())).withSelfRel();
            bookDTO.add(selfLink);
        });

        Link allBooksLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withRel("all-books");
        bookDTOs.forEach(dto -> dto.add(allBooksLink));

        // Increment custom metric
        meterRegistry.counter("books.retrieved", "endpoint", "getAllBooks").increment();

        return ResponseEntity.ok(bookDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id) {
        Book book = findBookById(id).orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));

        BookDTO bookDTO = bookMapper.toDTO(book);

        // Add HATEOAS links
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(id)).withSelfRel();
        bookDTO.add(selfLink);
        Link allBooksLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withRel("all-books");
        bookDTO.add(allBooksLink);

        // Increment custom metric
        meterRegistry.counter("books.retrieved", "endpoint", "getBookById").increment();

        return ResponseEntity.ok(bookDTO);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        if (bookDTO.getId() == null || bookDTO.getId() <= 0) {
            bookDTO.setId((books.size() + 1)); // Set a new ID if not provided or invalid
        }
        Book book = bookMapper.toEntity(bookDTO);
        books.add(book);

        BookDTO createdBookDTO = bookMapper.toDTO(book);

        // Add HATEOAS links
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(createdBookDTO.getId())).withSelfRel();
        createdBookDTO.add(selfLink);
        Link allBooksLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withRel("all-books");
        createdBookDTO.add(allBooksLink);

        // Increment custom metric
        meterRegistry.counter("books.created", "endpoint", "createBook").increment();

        return ResponseEntity.status(HttpStatus.CREATED).body(createdBookDTO);
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BookDTO> updateBook(@PathVariable Integer id, @Valid @RequestBody BookDTO bookDTO) {
        Book book = findBookById(id).orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        book.setIsbn(bookDTO.getIsbn());

        BookDTO updatedBookDTO = bookMapper.toDTO(book);

        // Add HATEOAS links
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(id)).withSelfRel();
        updatedBookDTO.add(selfLink);
        Link allBooksLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withRel("all-books");
        updatedBookDTO.add(allBooksLink);

        // Increment custom metric
        meterRegistry.counter("books.updated", "endpoint", "updateBook").increment();

        return ResponseEntity.ok(updatedBookDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        Book book = findBookById(id).orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
        books.remove(book);

        // Increment custom metric
        meterRegistry.counter("books.deleted", "endpoint", "deleteBook").increment();

        return ResponseEntity.noContent().build();
    }

    private Optional<Book> findBookById(Integer id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst();
    }
}
