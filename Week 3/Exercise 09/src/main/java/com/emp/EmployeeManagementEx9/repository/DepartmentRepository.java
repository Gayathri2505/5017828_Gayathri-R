package com.emp.EmployeeManagementEx9.repository;

import com.emp.EmployeeManagementEx9.dto.DepartmentDTO;
import com.emp.EmployeeManagementEx9.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT new com.emp.EmployeeManagementEx9.dto.DepartmentDTO(d.id, d.name) FROM Department d")
    List<DepartmentDTO> findAllDepartmentDTOs();
    
    @Query(name= "Department.findByName")
	Department findByName(String name);
}
