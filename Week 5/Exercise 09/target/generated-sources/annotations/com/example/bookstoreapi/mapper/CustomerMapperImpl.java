package com.example.bookstoreapi.mapper;

import com.example.bookstoreapi.dto.CustomerDTO;
import com.example.bookstoreapi.model.Customer;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-23T17:42:38+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO toDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        if ( customer.getId() != null ) {
            customerDTO.setId( customer.getId().longValue() );
        }
        customerDTO.setName( customer.getName() );
        customerDTO.setEmail( customer.getEmail() );

        return customerDTO;
    }

    @Override
    public Customer toEntity(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        if ( customerDTO.getId() != null ) {
            customer.setId( customerDTO.getId().intValue() );
        }
        customer.setName( customerDTO.getName() );
        customer.setEmail( customerDTO.getEmail() );

        return customer;
    }
}
