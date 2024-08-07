package com.library.service;

import org.springframework.stereotype.Service;
import com.library.repository.BookRepository;

@Service
public class BookService {
    private BookRepository bookRepo;

    public void setBookRepository(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void performService() {
        System.out.println("Service performed.");
        bookRepo.performRepositoryAction();
    }
}
