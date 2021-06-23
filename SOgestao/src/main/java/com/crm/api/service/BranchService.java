package com.crm.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Branch;
import com.crm.api.repositories.BranchRepository;

@Service
@Configurable
public class BranchService {
	
	@Autowired
	private BranchRepository branchRepository;

	public Branch save(Branch branch) {
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
	
	public void deleteBranch(Branch branch) {
		branchRepository.deleteById(branch.getId());
	}

	public boolean checkFields(Branch branch) {
		return branch.getAddress() != null &&
			   branch.getName() != null;
	}

	public Branch findById(Long id) {
		Branch branch = this.branchRepository.findById(id).get();
		return branch;
	}

}
