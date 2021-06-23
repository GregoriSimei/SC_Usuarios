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

import com.crm.api.business.BranchBusiness;
import com.crm.api.models.Branch;
import com.crm.api.service.BranchService;

@RestController
@RequestMapping("/branch")
public class BranchController{
	
	@Autowired
	private BranchBusiness branchBusiness;
	
	@Autowired
	private BranchService branchService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Branch create(@RequestBody Branch branch, @RequestParam("id") Long id) {
		branch = this.branchBusiness.createBranch(branch, id);
		return branch;
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public Branch update(@RequestBody Branch branch) {
		branch = this.branchBusiness.updateBranch(branch);
		return branch;
	}

	@GetMapping(produces = "application/json")
	public Branch getById(@RequestParam("id") Long id) {
		return this.branchService.findById(id);
	}

	@DeleteMapping(consumes = "application/json", produces = "application/json")
	public boolean delete(Branch object) {
		// TODO Auto-generated method stub
		return false;
	}

}
