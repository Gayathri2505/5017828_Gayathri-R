package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepo;
    
    public BookService(BookRepository bookRepo){
        this.bookRepo=bookRepo;
        System.out.println("\nConstructor Injection...\n");
    }
    public void setBookRepository(BookRepository bookRepo){
        this.bookRepo=bookRepo;
        System.out.println("\nSetter Injection....\n");
    }
    public void displayBookRepository(){
        System.out.println("The Book Repository is : "+bookRepo);
    }
}