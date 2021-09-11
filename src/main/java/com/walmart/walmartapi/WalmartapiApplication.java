package com.walmart.walmartapi;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.walmart.walmartapi.entity.Employee;
import com.walmart.walmartapi.repository.EmployeeRepository;

@SpringBootApplication
public class WalmartapiApplication {

	@Autowired
	EmployeeRepository empRepo;

	@PostConstruct
	public void createEmployee() {
		List<Employee> employeeList = Arrays.asList(new Employee(1, "Anand", "CSE", "35", "M"),
				new Employee(2, "Anjali", "Mech", "40", "F"), new Employee(3, "Binod", "CSE", "45", "M"));
		empRepo.saveAll(employeeList);
	}

	public static void main(String[] args) {
		SpringApplication.run(WalmartapiApplication.class, args);
	}

}
