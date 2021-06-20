package com.crm.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.models.User;
import com.crm.api.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PutMapping(consumes = "application/json", produces = "application/json")
	public User update(@RequestBody User user) {
		return this.userService.save(user);
	}

	@GetMapping(produces = "application/json")
	public User getById(@RequestParam(name = "id") Long id) {
		return this.userService.findUserById(id);
	}

	@DeleteMapping(consumes = "application/json", produces = "application/json")
	public boolean delete(User user) {
		return this.userService.delete(user);
	}
}
