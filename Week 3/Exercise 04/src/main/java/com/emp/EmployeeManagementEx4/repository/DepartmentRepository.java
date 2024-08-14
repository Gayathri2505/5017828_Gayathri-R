package com.emp.EmployeeManagementEx4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emp.EmployeeManagementEx4.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> 
{
    Department findByName(String name);
}