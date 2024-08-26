package com.example.bookstoreapi.mapper;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.model.Book;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-26T14:17:57+0530",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDTO toDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();

        bookDTO.setId( book.getId() );
        bookDTO.setTitle( book.getTitle() );
        bookDTO.setAuthor( book.getAuthor() );
        bookDTO.setPrice( book.getPrice() );
        bookDTO.setIsbn( book.getIsbn() );

        return bookDTO;
    }

    @Override
    public Book toEntity(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( bookDTO.getId() );
        book.setTitle( bookDTO.getTitle() );
        book.setAuthor( bookDTO.getAuthor() );
        if ( bookDTO.getPrice() != null ) {
            book.setPrice( bookDTO.getPrice() );
        }
        book.setIsbn( bookDTO.getIsbn() );

        return book;
    }
}
