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
	
	@Autowired
	private BranchRepository branchRepository;
	
	public Branch saveDeposit(Deposit deposit, long idfilial) {
		
		Branch branch = branchRepository.findById(idfilial);
		
		List<Deposit> deposits = branch.getDeposits();
		Long idDeposit = deposit.getId();
		boolean teste = idDeposit == null || idDeposit == 0;
		
		if(!teste) {
			for(Deposit depositCompare : deposits) {
				if(depositCompare.getId() == idDeposit) {
					depositCompare.setName(deposit.getName());
				}
			}
		}
		else {
			branch.setDeposit(deposit);
			depositRepository.save(deposit);
		}
		
		branchRepository.save(branch);
		
		return branch;
	}
	
	public Deposit getById(long id) {
		Deposit deposit = depositRepository.findById(id);
		return deposit;
	}
}
