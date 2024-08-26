package com.example.bookstoreapi.assembler;

import com.example.bookstoreapi.controller.BookController;
import com.example.bookstoreapi.dto.BookDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

public class BookModelAssembler extends RepresentationModelAssemblerSupport<BookDTO, EntityModel<BookDTO>> {

	@SuppressWarnings("unchecked")
	private static final Class<EntityModel<BookDTO>> RESOURCE_TYPE = (Class<EntityModel<BookDTO>>)(Class<?>)EntityModel.class;


	public BookModelAssembler() {
	    super(BookController.class, RESOURCE_TYPE);
	}


    @Override
    public EntityModel<BookDTO> toModel(BookDTO bookDTO) {
        EntityModel<BookDTO> bookModel = EntityModel.of(bookDTO);

        bookModel.add(linkTo(methodOn(BookController.class).getBookById(bookDTO.getId().intValue())).withSelfRel());
        bookModel.add(linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));

        return bookModel;
    }
}
