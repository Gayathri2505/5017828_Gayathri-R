package com.emp.EmployeeManagementEx4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emp.EmployeeManagementEx4.entity.Employee;
import com.emp.EmployeeManagementEx4.entity.Department;


import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> 
{

    List<Employee> findByDepartment(Department department);

    List<Employee> findByName(@Param("name") String name);

    Employee findByEmail(@Param("email") String email);
}