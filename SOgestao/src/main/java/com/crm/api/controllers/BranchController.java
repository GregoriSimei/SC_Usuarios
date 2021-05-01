	package com.crm.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.models.Branch;
import com.crm.api.models.Company;
import com.crm.api.service.BranchService;

@RestController
@RequestMapping("/branch")
public class BranchController {
	
	@Autowired
	private BranchService branchService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Company putCompany(@RequestBody Branch branch, @RequestParam(name = "id") long id ) {
		Company response = branchService.setBranchesById(branch, id);
		return response;
	}
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	public Branch updateBranch(@RequestBody Branch branch) {
		Branch response = branchService.updateBranch(branch);
		return response;
	}
	
	@DeleteMapping(consumes = "application/json", produces = "application/json")
	public Branch deleteBranch(@RequestBody Branch branch) {
		Branch response = branchService.deleteBranch(branch);
		return response;
	}

}
