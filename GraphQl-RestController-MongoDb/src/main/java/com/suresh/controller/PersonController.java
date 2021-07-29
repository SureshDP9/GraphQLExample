package com.suresh.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.events.MutationEvent;

import com.suresh.model.Person;
import com.suresh.repo.PersonRepo;

import graphql.ExecutionInput.Builder;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@RestController
@RequestMapping("/api")
public class PersonController {

	@Autowired
	private PersonRepo personRepo;

	@Value("classpath:graphql/schema.graphqls")
	private Resource schemaResource;

	private GraphQL graphql;



	@PostConstruct
	public void loadSchema() throws IOException {
		File schemaFile = schemaResource.getFile();
		TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildWiring();

		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
		graphql = GraphQL.newGraphQL(schema).build();
	}

	private RuntimeWiring buildWiring() {
		
		DataFetcher<List<Person>> fetcher1= data->{return (List<Person>) personRepo.findAll();};
		DataFetcher<Person> fetcher2 =data->{return personRepo.getById(data.getArgument("id"));};
		return RuntimeWiring.newRuntimeWiring()
				.type("Query",
						typeWriting -> typeWriting.dataFetcher("Persons", fetcher1).dataFetcher("Person", fetcher2))
				.build();
	}

	@PostMapping("/add")
	public String addPerson(@RequestBody List<Person> list) {
		personRepo.saveAll(list);
		return "inserted records:" + list.size();
	}

	@GetMapping("/getallpersons")
	public List<Person> getPersons() {

		return personRepo.findAll();
	}
    
	@GetMapping("/data")
	public ResponseEntity<Object> getData(@RequestBody String query) {
		ExecutionResult result = graphql.execute(query);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}
	
/*	@PostMapping("/updatePerson")
	public String updatePerson(@RequestBody String query){
		ExecutionResult result= graphql.execute(query); 
		if(result.isDataPresent())
			return "Updated";
		else
			return "Updation Failed";
	}*/
}












/*
 * 
 * package com.suresh.controller;


import org.springframework.beans.factory.annotation.Autowired;

import com.suresh.model.Person;
import com.suresh.repo.PersonRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@component
public class PersonDatafetcher implements DataFetcher<Person> {

	@Autowired
	private PersonRepo repository;
	
	@Override
	public Person get(DataFetchingEnvironment environment) throws Exception {
		Integer Id = environment.getArgument("id");
		return (Person) repository.getById(Id);
	
	}

}

 * 
 */
 
/*
 * package com.suresh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.suresh.model.Person;
import com.suresh.repo.PersonRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class PersonsDataFetcher implements DataFetcher<List<Person>> {

	
	@Autowired
	private PersonRepo repository;
	
	@Override
	public List<Person> get(DataFetchingEnvironment environment) throws Exception {
		
		return repository.findAll();
	}

}

 */
