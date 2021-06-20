package com.crm.api.controllers;

import javax.websocket.server.PathParam;

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
import com.crm.api.models.UserRequest;
import com.crm.api.service.UserRequestService;

@RestController
@RequestMapping("/request")
public class UserRequestController {
	
	@Autowired
	private UserRequestBusiness requestBusiness;
	
	@Autowired
	private UserRequestService requestService;

	@PostMapping(consumes = "application/json", produces = "application/json")
	public UserRequest create(@RequestBody UserRequest request) {
		return this.requestBusiness.createRequest(request);
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public UserRequest update(@RequestBody UserRequest request) {
		return this.requestBusiness.updateRequest(request);
	}

	@GetMapping(produces = "application/json")
	public UserRequest getRequest(@RequestParam(name = "id") Long id) {
		return this.requestService.findById(id);
	}
	
	@GetMapping(path = "/all", produces = "application/json")
	public Iterable<UserRequest> getRequests() {
		return this.requestService.getAll();
	}

	@DeleteMapping(consumes = "application/json", produces = "application/json")
	public boolean delete(UserRequest object) {
		// TODO Auto-generated method stub
		return false;
	}
}
