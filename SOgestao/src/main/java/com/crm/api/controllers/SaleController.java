package com.crm.api.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.models.Sale;

@RestController
@RequestMapping("/sale")
public class SaleController {
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Sale create(Sale object) {
		return null;
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public Sale update(Sale object) {
		// TODO Auto-generated method stub
		return null;
	}

	@GetMapping(produces = "application/json")
	public Sale getById(Sale id) {
		// TODO Auto-generated method stub
		return null;
	}

	@DeleteMapping(consumes = "application/json", produces = "application/json")
	public boolean delete(Sale object) {
		// TODO Auto-generated method stub
		return false;
	}
}
