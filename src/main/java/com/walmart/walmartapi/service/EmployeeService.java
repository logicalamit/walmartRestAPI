package com.walmart.walmartapi.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.walmartapi.entity.Employee;
import com.walmart.walmartapi.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository empRepo;

	public Map<String, Long> getEmployeesByGender(String gender) {
		Map<String, Long> empList = empRepo.findByGender(gender).stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		if(empList.size()==1){
			if(empList.containsKey("CSE")){
				empList.put("Mech", (long) 0);
			}else{
				empList.put("CSE", (long) 0);
			}
		}
		return empList;
	}

	public Map<String, String> getEmployeesByDepartment(String department) {
		Map<String, List<Employee>> empList = empRepo.findByDepartment(department).stream()
				.collect(Collectors.groupingBy(Employee::getGender));
		Iterator<Entry<String, List<Employee>>> itr = empList.entrySet().iterator();
		Map<String, String> finalEmpList = new HashMap<>();
		while (itr.hasNext()) {
			Entry<String, List<Employee>> entry = itr.next();
			finalEmpList.put(entry.getKey(), entry.getValue().stream().map(e -> e.getAge()).collect(Collectors.toList())
					.stream().collect(Collectors.joining("-")));
		}
		if (finalEmpList.size() == 1) {
			if (finalEmpList.containsKey("M")) {
				finalEmpList.put("F", "NA-NA");
			} else {
				finalEmpList.put("M", "NA-NA");
			}
		}
		return finalEmpList;
	}
}
