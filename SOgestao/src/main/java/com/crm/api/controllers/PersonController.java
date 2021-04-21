package com.crm.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.models.Person;
import com.crm.api.repositories.PersonRepository;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping(consumes = "application/json", produces = "application/json")
	public Iterable<Person> getAll(){
		Iterable<Person> people = personRepository.findAll();
		return people;
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Person salvaUsuario(@RequestBody Person person) {
		Person response = personRepository.save(person);
		return response;
	}

}
