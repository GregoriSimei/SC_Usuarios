package com.crm.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.business.UserBusiness;
import com.crm.api.business.UserRequestBusiness;
import com.crm.api.models.Person;
import com.crm.api.models.User;
import com.crm.api.models.UserRequest;
import com.crm.api.repositories.AddressRepository;
import com.crm.api.repositories.PersonRepository;
import com.crm.api.repositories.UserRepository;
import com.crm.api.repositories.UserRequestRepository;

@RestController
@RequestMapping("/user_request")
public class UserRequestController {
	
	@Autowired
	private UserRequestRepository userRequestRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRequestBusiness userRequestBusiness;
	
	@Autowired
	private UserBusiness userBusiness;
	
	
	@GetMapping(consumes = "application/json", produces = "application/json")
	public Iterable<UserRequest> getAll(){
		Iterable<UserRequest> response = userRequestRepository.findAll();
		
		return response;
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public UserRequest salvaUsuario(@RequestBody Person person) {
		UserRequest response = userRequestBusiness.createRequest(person);
		return response;
	}
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	public UserRequest approvalRequest(@RequestBody UserRequest request) {
		UserRequest response = userRequestBusiness.validateRequest(request);	
		return response;
	}

}
