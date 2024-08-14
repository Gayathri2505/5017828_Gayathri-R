package com.emp.EmployeeManagementEx10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.EmployeeManagementEx10.dto.*;
import com.emp.EmployeeManagementEx10.entity.*;
import com.emp.EmployeeManagementEx10.repository.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager entityManager;
    
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
    
    public List<EmployeeDTO> getAllEmployeeDTOs() {
        return employeeRepository.findAllEmployeeDTOs();
    }
    
    @Transactional
    public void batchInsert(List<Employee> employees) {
        int batchSize = 30;
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            if (employee.getId() == null) {
                // New entity, so persist
                entityManager.persist(employee);
            } else {
                // Existing entity, so merge
                entityManager.merge(employee);
            }
            if (i % batchSize == 0 && i > 0) {
                // Flush a batch of inserts and release memory
                entityManager.flush();
                entityManager.clear();
            }
        }
        // Flush remaining entities
        entityManager.flush();
        entityManager.clear();
    }
    
}
