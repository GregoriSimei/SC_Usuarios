package com.crm.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.models.Company;
import com.crm.api.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController{

	@Autowired
	private CompanyService companyService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Company create(@RequestBody Company company) {
		return this.companyService.save(company);
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public Company update(@RequestBody Company company) {
		return this.companyService.save(company);
	}
	
	@GetMapping(produces = "application/json")
	public Company getById(@RequestParam(name = "id") Long id) {
		return this.companyService.findById(id);
	}

	@DeleteMapping(consumes = "application/json", produces = "application/json")
	public boolean delete(@RequestBody Company object) {
		return false;
	}
	
}
