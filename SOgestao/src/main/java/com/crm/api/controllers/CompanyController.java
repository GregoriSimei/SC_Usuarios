package com.crm.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.models.Branch;
import com.crm.api.models.Company;
import com.crm.api.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	
	@Autowired
	private CompanyService companyBusiness;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Company postCompany(@RequestBody Company company) {
		Company response = companyBusiness.saveCompany(company);
		return response;
	}
	
}
