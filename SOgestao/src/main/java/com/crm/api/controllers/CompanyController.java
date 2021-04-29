package com.crm.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.business.CompanyBusiness;
import com.crm.api.models.Branch;
import com.crm.api.models.Company;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	private CompanyBusiness companyBusiness;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Company postCompany(@RequestBody Company company) {
		Company response = companyBusiness.saveCompany(company);
		return response;
	}
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	public Company putCompany(@RequestBody Branch branch, @RequestParam(name = "id") long id ) {
		Company response = companyBusiness.setBranchesById(branch, id);
		
		return response;
	}
	
	@RequestMapping("/branch/update")
	@PutMapping(consumes = "application/json", produces = "application/json")
	public Branch updateBranch(@RequestBody Branch branch) {
		Branch response = companyBusiness.updateBranch(branch);
		return response;
	}
	
	@RequestMapping("/branch/delete")
	@PutMapping(consumes = "application/json", produces = "application/json")
	public Branch deleteBranch(@RequestBody Branch branch) {
		Branch response = companyBusiness.deleteBranch(branch);
		return response;
	}
}
