package com.example.bookstoreapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Include only non-null fields
public class BookDTO {
    
    @JsonProperty("book_id")
    private int id;
    
    @JsonProperty("book_title")
    private String title;
    
    @JsonProperty("book_author")
    private String author;
    
    @JsonProperty("book_price")
    private double price;
    
    @JsonProperty("book_isbn")
    private String isbn;
}
