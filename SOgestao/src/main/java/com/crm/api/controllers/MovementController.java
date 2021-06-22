package com.crm.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.business.MovementBusiness;
import com.crm.api.models.Movement;

@RestController
@RequestMapping("/movement")
public class MovementController {
	
	@Autowired
	private MovementBusiness movementBusiness;

	@PostMapping(consumes = "application/json", produces = "application/json")
	public Movement create(@RequestBody Movement movement) {
		return this.movementBusiness.createMovement(movement);
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public Movement update(Movement movement) {
		// TODO Auto-generated method stub
		return null;
	}

	@GetMapping(produces = "application/json")
	public Movement getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@DeleteMapping(consumes = "application/json", produces = "application/json")
	public boolean delete(Movement movement) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
