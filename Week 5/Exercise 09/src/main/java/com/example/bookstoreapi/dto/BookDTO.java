package com.example.bookstoreapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.hateoas.RepresentationModel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO extends RepresentationModel<BookDTO>{

    @JsonProperty("book_id")
    @NotNull(message="Id must not be blank")
    @Min(value = 1, message = "ID must be a positive number")
    private Integer id;

    @NotNull
    @Size(min = 1, max = 255)
    @JsonProperty("book_title")
    private String title;

    @NotNull
    @Size(min = 1, max = 255)
    @JsonProperty("book_author")
    private String author;

    @NotNull
    @Min(0)
    @JsonProperty("book_price")
    private Double price;

    @NotNull
    @Size(min = 10, max = 13)
    @JsonProperty("book_isbn")
    private String isbn;
}
