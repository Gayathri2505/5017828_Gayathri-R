package com.emp.EmployeeManagementEx9.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.emp.EmployeeManagementEx9.repository.*;
import com.emp.EmployeeManagementEx9.dto.DepartmentDTO;
import com.emp.EmployeeManagementEx9.entity.*;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department createOrUpdateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
    
    public List<DepartmentDTO> getAllDepartmentDTOs() {
        return departmentRepository.findAllDepartmentDTOs();
    }
}