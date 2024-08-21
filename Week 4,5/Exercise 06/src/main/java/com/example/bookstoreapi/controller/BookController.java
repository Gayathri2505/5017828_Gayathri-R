package com.example.bookstoreapi.controller;


import com.example.bookstoreapi.exception.*;
import com.example.bookstoreapi.model.Book;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();
        
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        if (book.getId() <= 0) {
            throw new BadRequestException("Invalid book ID");
        }
        books.add(book);
        return ResponseEntity.status(201).body(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        for (Book book : books) {
            if (book.getId() == id) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setPrice(updatedBook.getPrice());
                book.setIsbn(updatedBook.getIsbn());
                HttpHeaders headers = new HttpHeaders();
                headers.add("Custom-Header", "BookUpdated");
                return new ResponseEntity<>(book, headers, HttpStatus.OK);
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
    public ResponseEntity<List<Book>> getAllBooks() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "AllBooksListed");
        return new ResponseEntity<>(books, headers, HttpStatus.OK);
    }
}
