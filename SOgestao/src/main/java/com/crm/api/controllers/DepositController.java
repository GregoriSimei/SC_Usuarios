package com.crm.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.models.Branch;
import com.crm.api.models.Deposit;
import com.crm.api.service.DepositService;

@RestController
@RequestMapping("/deposit")
public class DepositController {
	
	@Autowired
	private DepositService depositBusiness;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Branch postDeposit(@RequestBody Deposit deposit, @RequestParam ("id") long idfilial ) {
		return depositBusiness.saveDeposit(deposit, idfilial);
	}
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	public Branch updateDeposit(@RequestBody Deposit deposit, @RequestParam ("id") long idfilial ) {
		return depositBusiness.saveDeposit(deposit, idfilial);
	}

}
