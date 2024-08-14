package com.emp.EmployeeManagementEx10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.emp.EmployeeManagementEx10.dto.DepartmentDTO;
import com.emp.EmployeeManagementEx10.entity.Department;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT new com.emp.EmployeeManagementEx10.dto.DepartmentDTO(d.id, d.name) FROM Department d")
    List<DepartmentDTO> findAllDepartmentDTOs();
    
    @Query(name= "Department.findByName")
	Department findByName(String name);
}
