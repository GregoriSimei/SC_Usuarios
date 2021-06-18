package com.crm.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Branch;
import com.crm.api.models.Deposit;
import com.crm.api.repositories.BranchRepository;
import com.crm.api.repositories.DepositRepository;

@Service
@Configurable
public class DepositService {
	
	@Autowired
	private DepositRepository depositRepository;
	
	public Deposit save(Deposit deposit) {
		
		
		return this.depositRepository.save(deposit);
	}
	
	public Deposit getById(long id) {
		Deposit deposit = depositRepository.findById(id);
		return deposit;
	}
}
