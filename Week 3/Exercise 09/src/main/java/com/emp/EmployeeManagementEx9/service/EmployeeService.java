package com.emp.EmployeeManagementEx9.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.emp.EmployeeManagementEx9.repository.*;
import com.emp.EmployeeManagementEx9.entity.*;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createOrUpdateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
