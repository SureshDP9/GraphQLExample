package com.suresh.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmpInput {

	 int employeeId;
	 String employeeName;
	 String department;
	 Address address;
   
}
