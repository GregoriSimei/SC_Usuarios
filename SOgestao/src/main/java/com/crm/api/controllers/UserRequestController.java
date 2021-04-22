package com.crm.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.business.UserRequestBusiness;
import com.crm.api.models.Person;
import com.crm.api.models.UserRequest;
import com.crm.api.repositories.PersonRepository;
import com.crm.api.repositories.UserRequestRepository;

@RestController
@RequestMapping("/user_request")
public class UserRequestController {
	
	@Autowired
	private UserRequestRepository userRequestRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	private UserRequestBusiness userRequestBusiness = new UserRequestBusiness();
	
	
	@GetMapping(consumes = "application/json", produces = "application/json")
	public Iterable<UserRequest> getAll(){
		
		Iterable<UserRequest> response = userRequestRepository.findAll();
		
		return response;
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public UserRequest salvaUsuario(@RequestBody Person person) {
		
		UserRequest response = null;
		UserRequest userRequest = userRequestBusiness.createRequest(person);
		
		if(userRequest != null) {
			personRepository.save(person);
			response = userRequestRepository.save(userRequest);
		}
		
		return response;
	}

}
