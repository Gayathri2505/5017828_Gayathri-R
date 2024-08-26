package com.example.bookstoreapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    private double price;
    private String isbn;
    
    @Version
    private Integer version;
    
    public Book(Integer id,String title, String author,double price ,String isbn) {
        this.id=id;
    	this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
    }

}
