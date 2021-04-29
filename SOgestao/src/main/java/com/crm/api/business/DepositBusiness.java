package com.crm.api.business;

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
public class DepositBusiness {
	
	@Autowired
	private DepositRepository depositRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	public Branch saveDeposit(Deposit deposit, long idfilial) {
		
		Branch branch = branchRepository.findById(idfilial);
		branch.setDeposit(deposit);
		depositRepository.save(deposit);
		branchRepository.save(branch);
		
		return branch;
	}
}
