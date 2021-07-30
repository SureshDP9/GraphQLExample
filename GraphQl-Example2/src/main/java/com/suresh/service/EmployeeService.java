package com.suresh.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.suresh.models.EmpInput;
import com.suresh.models.Employee;
import com.suresh.repo.EmployeeRepo;

@Service
public class EmployeeService implements GraphQLQueryResolver,GraphQLMutationResolver {

	@Autowired
	private EmployeeRepo empRepo;

	/*@PostMapping("/add")
	public ResponseEntity<List<Employee>> addData(@RequestBody List<Employee> emp) {
		
		List<Employee> result=empRepo.saveAll(emp);
		return new ResponseEntity<List<Employee>>(result,HttpStatus.OK);
	}
	
	/*
	@PostMapping("/addaddress")
	public ResponseEntity<Address> addAddress(@RequestBody Address addr) {
		
		Address result=addRepo.save(addr);
		return new ResponseEntity<Address>(result,HttpStatus.OK);
	}
	*/
	public List<Employee> getAllEmployees(){
		return empRepo.findAll();
	}
	public Optional<Employee> getEmployeeById(int id) {
		return empRepo.findById(id);
	}
	
	@Transactional
	public Employee createEmployee(EmpInput emp)
	{
		Employee employee = new Employee();
		employee.setEmployeeId(emp.getEmployeeId());
		employee.setEmployeeName(emp.getEmployeeName());
		employee.setDepartment(emp.getDepartment());
		employee.setAddress(emp.getAddress());
		
	    empRepo.save(employee);
		return employee;
		
	}
	
	public Employee updateDepartment(Integer id,String dept) {
		Employee emp= empRepo.findById(id).get();
		emp.setDepartment(dept);
		return empRepo.save(emp);
	}
	
}

