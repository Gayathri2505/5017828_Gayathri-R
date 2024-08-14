package com.emp.EmployeeManagementEx4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"com.emp.EmployeeManagementEx4.controller","com.emp.EmployeeManagementEx4.service"})
@EntityScan("com.emp.EmployeeManagementEx4.entity")
@EnableJpaRepositories("com.emp.EmployeeManagementEx4.repository")
@SpringBootApplication
public class EmployeeManagementEx4Application {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementEx4Application.class, args);
	}

}
