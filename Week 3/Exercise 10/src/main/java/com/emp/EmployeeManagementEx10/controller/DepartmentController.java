package com.emp.EmployeeManagementEx10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.emp.EmployeeManagementEx10.dto.DepartmentDTO;
import com.emp.EmployeeManagementEx10.entity.*;
import com.emp.EmployeeManagementEx10.service.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department createdDepartment = departmentService.createOrUpdateDepartment(department);
        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        return department.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }
    
    @GetMapping("/dto")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartmentDTOs() {
        List<DepartmentDTO> departmentDTOs = departmentService.getAllDepartmentDTOs();
        return ResponseEntity.ok(departmentDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        if (!departmentService.getDepartmentById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        department.setId(id);
        Department updatedDepartment = departmentService.createOrUpdateDepartment(department);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        if (!departmentService.getDepartmentById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}