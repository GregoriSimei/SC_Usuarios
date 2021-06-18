package com.crm.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Branch;
import com.crm.api.models.Company;
import com.crm.api.repositories.AddressRepository;
import com.crm.api.repositories.BranchRepository;
import com.crm.api.repositories.CompanyRepository;

@Service
@Configurable
public class BranchService {
	
	@Autowired
	private BranchRepository branchRepository;

	private Branch save(Branch branch) {
		branch = branch.getId() != null?
				this.create(branch):
				this.update(branch);
		
		return this.branchRepository
				.save(branch);
	}
	
	private Branch update(Branch branch) {
		return branch;
	}
	
	private Branch create(Branch branch) {
		return branch;
	}
	
	public Branch updateBranch(Branch branch) {
		Branch bra = branchRepository.findById(branch.getId()).get();
		bra.setName(branch.getName());
		branchRepository.save(bra);
		return bra;
		
	}
	
	public void deleteBranch(Branch branch) {
		branchRepository.deleteById(branch.getId());
	}

}
