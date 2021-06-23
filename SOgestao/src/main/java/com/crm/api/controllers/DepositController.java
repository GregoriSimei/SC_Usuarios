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

import com.crm.api.business.DepositBusiness;
import com.crm.api.models.Deposit;
import com.crm.api.service.DepositService;

@RestController
@RequestMapping("/deposit")
public class DepositController{
	
	@Autowired
	private DepositBusiness depositBusiness;
	
	@Autowired
	private DepositService depositService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Deposit create(@RequestBody Deposit deposit, @RequestParam("id") Long id) {
		return this.depositBusiness.createDeposit(deposit, id);
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public Deposit update(@RequestBody Deposit deposit) {
		return this.depositService.saveOnlyName(deposit);
	}

	@GetMapping(produces = "application/json")
	public Deposit getById(@RequestParam("id") Long id) {
		return this.depositService.findById(id);
	}

	@DeleteMapping(consumes = "application/json", produces = "application/json")
	public boolean delete(Deposit object) {
		// TODO Auto-generated method stub
		return false;
	}

}
