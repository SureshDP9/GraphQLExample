package com.suresh.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.suresh.model.Person;

@Service
public interface PersonRepo extends MongoRepository<Person,Integer>{

	public Person getById(Integer id);

}
