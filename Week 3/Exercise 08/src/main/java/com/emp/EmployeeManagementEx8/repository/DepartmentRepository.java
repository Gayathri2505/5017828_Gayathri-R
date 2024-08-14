package com.emp.EmployeeManagementEx8.repository;

import com.emp.EmployeeManagementEx8.dto.DepartmentDTO;
import com.emp.EmployeeManagementEx8.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT new com.emp.EmployeeManagementEx8.dto.DepartmentDTO(d.id, d.name) FROM Department d")
    List<DepartmentDTO> findAllDepartmentDTOs();
    
    @Query(name= "Department.findByName")
	Department findByName(String name);
}
