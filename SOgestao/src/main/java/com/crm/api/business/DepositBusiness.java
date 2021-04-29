package com.crm.api.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Branch;
import com.crm.api.models.Company;
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
	public Branch saveDeposit(List<Deposit> deps, long idfilial) {
		
		Branch branch = branchRepository.findById(idfilial);
		for (Deposit dep : branch.getDeposits()) {
			if(!deps.contains(dep.getId())) {	// se o dep nao estiver cadastrado na filial
				branch.setDeposits(deps);
				branchRepository.save(branch);
			}
			else {
				System.out.println("deposito já cadastrado nessa filial");
			}
		}
		
		return branch;
	}
	

}
