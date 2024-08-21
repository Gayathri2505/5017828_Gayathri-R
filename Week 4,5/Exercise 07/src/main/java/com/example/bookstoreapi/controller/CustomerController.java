package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.CustomerDTO;
import com.example.bookstoreapi.mapper.CustomerMapper;
import com.example.bookstoreapi.model.Customer;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>();
    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        customers.add(customer);
        return customerMapper.toDTO(customer);
    }

    @PostMapping("/register")
    public CustomerDTO registerCustomer(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) {
        Customer customer = new Customer(customers.size() + 1, name, email, password);
        customers.add(customer);
        return customerMapper.toDTO(customer);
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customers.stream().map(customerMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable int id) {
        return customers.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .map(customerMapper::toDTO)
                .orElse(null); // Handle this as needed (e.g., throw an exception)
    }
}
