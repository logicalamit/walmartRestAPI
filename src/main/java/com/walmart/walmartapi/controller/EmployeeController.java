package com.walmart.walmartapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.walmartapi.service.EmployeeService;

@RestController
@RequestMapping("/walmart/employee/")
public class EmployeeController {
	@Autowired
	EmployeeService empService;
	
	@GetMapping("department/{name}")
	public Object getEmployeeByDepartment(@PathVariable(value = "name") String department){
		return empService.getEmployeesByDepartment(department);
		
	}
	@GetMapping("gender/{type}")
	public Object getEmployeeByGender(@PathVariable(value = "type") String genderType){
		return empService.getEmployeesByGender(genderType);
	}
	
}
