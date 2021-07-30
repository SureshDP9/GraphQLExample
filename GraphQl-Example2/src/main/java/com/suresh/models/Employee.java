package com.suresh.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection="Employee")
public class Employee {

	public Employee() {
	}
	@Id
	private int employeeId;
	private String employeeName;
	private String department;
	private Address address;
		
}
