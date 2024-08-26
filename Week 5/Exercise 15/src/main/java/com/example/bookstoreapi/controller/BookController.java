package com.example.bookstoreapi.controller;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.service.MetricService;
import com.example.bookstoreapi.exception.BadRequestException;
import com.example.bookstoreapi.exception.BookNotFoundException;
import com.example.bookstoreapi.mapper.BookMapper;
import com.example.bookstoreapi.model.Book;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


    
@RestController
@RequestMapping("/books")
public class BookController {

	private final MetricService metricService;

    @Autowired
    public BookController(MetricService metricService) {
        this.metricService = metricService;
    }
	
    private List<Book> books = new ArrayList<>();
    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @GetMapping("/search")
    public List<BookDTO> searchBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author) {
        return books.stream()
                .filter(book -> (title == null || book.getTitle().toLowerCase().contains(title.toLowerCase())) &&
                                (author == null || book.getAuthor().toLowerCase().contains(author.toLowerCase())))
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        if (bookDTO.getId() <= 0) {
            throw new BadRequestException("Invalid book ID");
        }
        Book book = bookMapper.toEntity(bookDTO);
        books.add(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BookCreated");
        
        metricService.incrementBookCreationCounter();  // Increment the custom metric

        
        return new ResponseEntity<>(bookMapper.toDTO(book), headers, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable int id) {
        Book book = books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));

        BookDTO bookDTO = bookMapper.toDTO(book);

        
        bookDTO.add(linkTo(methodOn(BookController.class).getBookById(id)).withSelfRel());
        bookDTO.add(linkTo(methodOn(BookController.class).getAllBooks()).withRel("all-books").withType("GET"));
        bookDTO.add(linkTo(methodOn(BookController.class).deleteBook(id)).withRel("delete-book").withType("DELETE"));
        bookDTO.add(linkTo(methodOn(BookController.class).updateBook(id, bookDTO)).withRel("update-book").withType("PUT"));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BookFound");

        return new ResponseEntity<>(bookDTO, headers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable int id, @Valid @RequestBody BookDTO updatedBookDTO) {
              for (Book book : books) {
            if (book.getId() == id) {
                book.setTitle(updatedBookDTO.getTitle());
                book.setAuthor(updatedBookDTO.getAuthor());
                book.setPrice(updatedBookDTO.getPrice());
                book.setIsbn(updatedBookDTO.getIsbn());
                HttpHeaders headers = new HttpHeaders();
                headers.add("Custom-Header", "BookUpdated");
                return new ResponseEntity<>(bookMapper.toDTO(book), headers, HttpStatus.OK);
            }
        }
        throw new BookNotFoundException("Book not found with ID: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        boolean removed = books.removeIf(book -> book.getId() == id);
        if (removed) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Custom-Header", "BookDeleted");
            return new ResponseEntity<>("Book with ID " + id + " deleted.", headers, HttpStatus.NO_CONTENT);
        } else {
            throw new BookNotFoundException("Book not found with ID: " + id);
        }
    }

    
    @Operation(summary = "Get all books", description = "Retrieve a list of all books")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of books")    
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> bookDTOs = books.stream()
                .map(book -> {
                    BookDTO bookDTO = bookMapper.toDTO(book);
                    bookDTO.add(linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel());
                    bookDTO.add(linkTo(methodOn(BookController.class).deleteBook(book.getId())).withRel("delete-book").withType("DELETE"));
                    bookDTO.add(linkTo(methodOn(BookController.class).deleteBook(book.getId())).withRel("update-book").withType("PUT"));
                    return bookDTO;
                })
                .collect(Collectors.toList());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "AllBooksListed");

        return new ResponseEntity<>(bookDTOs, headers, HttpStatus.OK);
    }

}
