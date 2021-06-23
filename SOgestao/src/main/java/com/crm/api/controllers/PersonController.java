package com.crm.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.business.UserRequestBusiness;
import com.crm.api.models.Person;
import com.crm.api.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private UserRequestBusiness requestBusiness;
	
	@Autowired
	private PersonService personService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Person create(@RequestBody Person person) {
		return this.requestBusiness.savePerson(person);
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public Person update(@RequestBody Person person) {
		return this.personService.update(person);
	}

	@GetMapping(produces = "application/json")
	public Person getById(@RequestParam("id") Long id) {
		return this.personService.findById(id);
	}

	@DeleteMapping(consumes = "application/json", produces = "application/json")
	public boolean delete(Person person) {
		return false;
	}

}
