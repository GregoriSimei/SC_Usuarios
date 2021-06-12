package com.crm.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.models.Person;
import com.crm.api.models.UserRequest;
import com.crm.api.service.UserRequestService;

@RestController
@RequestMapping("/user_request")
public class UserRequestController {

	@Autowired
	private UserRequestService userRequestBusiness;
	
	@GetMapping(produces = "application/json")
	public Iterable<UserRequest> getAll(){
		Iterable<UserRequest> response = userRequestBusiness.getAll();
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
