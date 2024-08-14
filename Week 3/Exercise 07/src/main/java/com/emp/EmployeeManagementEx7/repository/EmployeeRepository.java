package com.emp.EmployeeManagementEx7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emp.EmployeeManagementEx7.entity.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> 
{

	@Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName")
    List<Employee> findEmployeesByDepartmentName(@Param("departmentName") String departmentName);

    @Query("SELECT e FROM Employee e WHERE e.name LIKE %:name%")
    List<Employee> findEmployeesByNameContaining(@Param("name") String name);

    @Query(value = "SELECT * FROM employees WHERE email = :email", nativeQuery = true)
    Employee findByEmailNative(@Param("email") String email);
}