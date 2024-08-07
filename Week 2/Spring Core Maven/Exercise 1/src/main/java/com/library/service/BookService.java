package com.library.service;

import com.library.repository.BookRepository;

public class BookService 
{
    
    private BookRepository bookRepo;

    public void setBookRepository(BookRepository bookRepo) {
        this.setBookRepo(bookRepo);
    }

	public BookRepository getBookRepo() {
		return bookRepo;
	}

	public void setBookRepo(BookRepository bookRepo) {
		this.bookRepo = bookRepo;
	}
    
}
