package com.emp.EmployeeManagementEx10.projection;


import org.springframework.beans.factory.annotation.Value;

public interface DepartmentProjection {
    @Value("#{target.id}")
    long getId();
    @Value("#{target.name}")
    String getName();
    @Value("#{target.employees.size()}")
    int getEmployeeCount();
}