package com.crm.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.models.User;
import com.crm.api.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping(produces = "application/json")
	public Iterable<User> getAll(){
		Iterable<User> users = userService.findAll();
		return users;
	}
	
	@PostMapping(consumes = "application/json", produces = "applcation/json")
	public User save(@RequestBody User user) {
		return this.userService.save(user);
	}

	@DeleteMapping(consumes = "application/json", produces = "application/json")
	public boolean deleteUser(@RequestBody User user) {
		return this.userService.delete(user);
	}
}
