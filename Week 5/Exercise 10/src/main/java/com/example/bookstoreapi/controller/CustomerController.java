package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.CustomerDTO;
import com.example.bookstoreapi.exception.CustomerNotFoundException;
import com.example.bookstoreapi.mapper.CustomerMapper;
import com.example.bookstoreapi.model.Customer;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>();
    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customerDTOs = customers.stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());

        // Add HATEOAS links to each customerDTO
        customerDTOs.forEach(customerDTO -> {
            Integer id = customerDTO.getId(); // Use Integer here
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(id)).withSelfRel();
            customerDTO.add(selfLink);
        });

        // Add link to self (all customers endpoint)
        Link allCustomersLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getAllCustomers()).withRel("all-customers");
        customerDTOs.forEach(dto -> dto.add(allCustomersLink));

        return ResponseEntity.ok(customerDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id) {
        Customer customer = findCustomerById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id));

        CustomerDTO customerDTO = customerMapper.toDTO(customer);

        // Add self link
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(id)).withSelfRel();
        customerDTO.add(selfLink);

        // Add link to all customers
        Link allCustomersLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getAllCustomers()).withRel("all-customers");
        customerDTO.add(allCustomersLink);

        return ResponseEntity.ok(customerDTO);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        if (customerDTO.getId() == null || customerDTO.getId() <= 0) {
            customerDTO.setId(customers.size() + 1); // Set a new ID if not provided or invalid
        }
        Customer customer = customerMapper.toEntity(customerDTO);
        customers.add(customer);

        CustomerDTO createdCustomerDTO = customerMapper.toDTO(customer);

        // Add self link
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(createdCustomerDTO.getId())).withSelfRel();
        createdCustomerDTO.add(selfLink);

        // Add link to all customers
        Link allCustomersLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getAllCustomers()).withRel("all-customers");
        createdCustomerDTO.add(allCustomersLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Integer id, @Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = findCustomerById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id));
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());

        CustomerDTO updatedCustomerDTO = customerMapper.toDTO(customer);

        // Add self link
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(id)).withSelfRel();
        updatedCustomerDTO.add(selfLink);

        // Add link to all customers
        Link allCustomersLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getAllCustomers()).withRel("all-customers");
        updatedCustomerDTO.add(allCustomersLink);

        return ResponseEntity.ok(updatedCustomerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        Customer customer = findCustomerById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id));
        customers.remove(customer);
        return ResponseEntity.noContent().build();
    }

    private Optional<Customer> findCustomerById(Integer id) {
        return customers.stream().filter(customer -> customer.getId().equals(id)).findFirst();
    }
}
