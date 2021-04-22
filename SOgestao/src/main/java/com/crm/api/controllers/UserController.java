package com.crm.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.models.User;
import com.crm.api.repositories.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository userRepository;

	@GetMapping(produces = "application/json")
	public Iterable<User> getAll(){
		Iterable<User> users = userRepository.findAll();
		return users;
	}
	

}
