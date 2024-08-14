package com.emp.EmployeeManagementEx10;

import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJpaAuditing
public class EmployeeManagementEx10Application {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementEx10Application.class, args);
	}

}
