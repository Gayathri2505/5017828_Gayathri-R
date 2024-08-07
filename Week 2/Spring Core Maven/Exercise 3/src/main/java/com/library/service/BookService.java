package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepo;

    public void setBookRepository(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public BookRepository getBookRepository() {
        return bookRepo;
    }

    public void manageBooks() {
        bookRepo.fetchBooks();
    }
}
