package com.crm.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.business.CompanyBusiness;
import com.crm.api.business.DepositBusiness;
import com.crm.api.models.Branch;
import com.crm.api.models.Company;
import com.crm.api.models.Deposit;

@RestController
@RequestMapping("/deposit")
public class DepositController {
	
	@Autowired
	private DepositBusiness depositBusiness;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Branch postDeposit(@RequestBody List<Deposit> listdep, @RequestParam ("id") long idfilial ) {
		return depositBusiness.saveDeposit(listdep, idfilial);

	}

}
