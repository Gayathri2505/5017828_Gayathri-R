package com.example.bookstoreapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
//import com.fasterxml.jackson.dataformat.xml.annotation.XmlRootElement;


import org.springframework.hateoas.RepresentationModel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;


@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "BookDTO")
public class BookDTO extends RepresentationModel<BookDTO>{

    @JsonProperty("id")
    @XmlElement(name="id")
    @NotNull(message="Id must not be blank")
    @Min(value = 1, message = "ID must be a positive number")
    private Integer id;

    @NotNull
    @Size(min = 1, max = 255)
    @JsonProperty("title")
    @XmlElement(name="title")
    private String title;

    @NotNull
    @Size(min = 1, max = 255)
    @JsonProperty("author")
    @XmlElement(name="author")
    private String author;

    @NotNull
    @Min(0)
    @JsonProperty("price")
    @XmlElement(name="price")
    private Double price;

    @NotNull
    @Size(min = 10, max = 13)
    @JsonProperty("isbn")
    @XmlElement(name="isbn")
    private String isbn;
    
    public BookDTO(Integer id,String title, String author, String isbn, double price) {
        this.id=id;
    	this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
    }
}
