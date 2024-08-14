package com.emp.EmployeeManagementEx5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emp.EmployeeManagementEx5.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> 
{
	@Query(name= "Department.findByName")
	Department findByName(String name);
}