package com.crm.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Person;
import com.crm.api.repositories.PersonRepository;

@Service
@Configurable
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;

	public boolean checkFields(Person person) {
		return person.getBirth() != null &&
			   person.getCell() != null &&
			   person.getCpf() != null &&
			   person.getName() != null;
	}

	public Person save(Person person) {
		person = this.personRepository.save(person);
		return person;
	}

	public Person getPerson(Person person) {
		Long id = person.getId();
		return this.findById(id);
	}
	
	public Person findById(Long id) {
		return this.personRepository.findById(id).get();
	}

	public Person update(Person person) {
		person = person.getId() != null?
				this.save(person):
				null;
		
		return person;
	}

}
