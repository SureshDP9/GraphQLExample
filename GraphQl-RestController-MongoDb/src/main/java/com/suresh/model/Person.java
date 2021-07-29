package com.suresh.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="test")
public class Person {

	@Id
	private int id;
	private String name;
	private String mobile;
	private String email;
	private String[] address;
	
}