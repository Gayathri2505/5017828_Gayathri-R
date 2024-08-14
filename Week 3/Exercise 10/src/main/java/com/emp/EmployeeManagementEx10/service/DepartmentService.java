package com.emp.EmployeeManagementEx10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.EmployeeManagementEx10.dto.DepartmentDTO;
import com.emp.EmployeeManagementEx10.entity.*;
import com.emp.EmployeeManagementEx10.repository.*;

import java.util.List;
import java.util.Optional;

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