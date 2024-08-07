package com.library.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    public BookRepository(){
        System.out.println("Book Repository bean ");
    }
    public void performRepositoryAction() {
        System.out.println("Repository action performed.");
    }
}