package com.emp.EmployeeManagementEx8.repository;

import com.emp.EmployeeManagementEx8.dto.EmployeeDTO;
import com.emp.EmployeeManagementEx8.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT new com.emp.EmployeeManagementEx8.dto.EmployeeDTO(e.id, e.name, e.email) FROM Employee e")
    List<EmployeeDTO> findAllEmployeeDTOs();

    @Query("SELECT new com.emp.EmployeeManagementEx8.dto.EmployeeDTO(e.id, e.name, e.email) FROM Employee e WHERE e.name LIKE %:name%")
    List<EmployeeDTO> findEmployeesByNameContaining(@Param("name") String name);
    
    @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName")
    List<Employee> findEmployeesByDepartmentName(@Param("departmentName") String departmentName);

    @Query(value = "SELECT * FROM employees WHERE email = :email", nativeQuery = true)
    Employee findByEmailNative(@Param("email") String email);
}
