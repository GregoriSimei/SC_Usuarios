package com.crm.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.business.MovementBusiness;
import com.crm.api.models.Movement;

@RestController
@RequestMapping("/movement")
public class MovementController {

	@Autowired
	private MovementBusiness movementBussine;
	
	@GetMapping(consumes = "application/json", produces = "application/json")
	public List<Movement> getAll(@RequestParam("id") long id){
		List<Movement> response = movementBussine.getAll(id);
		return response;
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Movement postMovement(Movement movement) {
		Movement response = movementBussine.saveMovement(movement);
		return response;
	}
	
}
