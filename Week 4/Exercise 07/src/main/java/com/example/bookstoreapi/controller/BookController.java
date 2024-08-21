package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.exception.BadRequestException;
import com.example.bookstoreapi.exception.BookNotFoundException;
import com.example.bookstoreapi.mapper.BookMapper;
import com.example.bookstoreapi.model.Book;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

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

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        if (bookDTO.getId() <= 0) {
            throw new BadRequestException("Invalid book ID");
        }
        Book book = bookMapper.toEntity(bookDTO);
        books.add(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BookCreated");
        return new ResponseEntity<>(bookMapper.toDTO(book), headers, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .map(book -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Custom-Header", "BookFound");
                    return new ResponseEntity<>(bookMapper.toDTO(book), headers, HttpStatus.OK);
                })
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable int id, @RequestBody BookDTO updatedBookDTO) {
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

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "AllBooksListed");
        return new ResponseEntity<>(books.stream().map(bookMapper::toDTO).collect(Collectors.toList()), headers, HttpStatus.OK);
    }
}
