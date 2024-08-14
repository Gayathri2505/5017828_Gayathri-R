package com.emp.EmployeeManagementEx10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.emp.EmployeeManagementEx10.dto.*;
import com.emp.EmployeeManagementEx10.entity.*;
import com.emp.EmployeeManagementEx10.service.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/emp")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createOrUpdateEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/dto")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeeDTOs() {
        List<EmployeeDTO> employeeDTOs = employeeService.getAllEmployeeDTOs();
        return ResponseEntity.ok(employeeDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        if (!employeeService.getEmployeeById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        employee.setId(id);
        Employee updatedEmployee = employeeService.createOrUpdateEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (!employeeService.getEmployeeById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}