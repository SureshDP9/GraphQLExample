package com.suresh.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.suresh.models.Employee;

@Service
public interface EmployeeRepo extends MongoRepository<Employee, Integer> {

}
