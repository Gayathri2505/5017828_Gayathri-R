package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.CustomerDTO;
import com.example.bookstoreapi.exception.CustomerNotFoundException;
import com.example.bookstoreapi.mapper.CustomerMapper;
import com.example.bookstoreapi.model.Customer;
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
        return ResponseEntity.ok(customers.stream().map(customerMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id) {
        Customer customer = customers.stream()
                .filter(c -> c.getId().equals(id)) // Use equals() for comparison with Long objects
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id));
        return ResponseEntity.ok(customerMapper.toDTO(customer));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        if (customerDTO.getId() == null || customerDTO.getId() <= 0) {
            customerDTO.setId((long) (customers.size() + 1)); // Set a new ID if not provided or invalid
        }
        Customer customer = customerMapper.toEntity(customerDTO);
        customers.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerMapper.toDTO(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Integer id, @Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = findCustomerById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id));
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        return ResponseEntity.ok(customerMapper.toDTO(customer));
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
