package com.crm.api.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Address;
import com.crm.api.models.Branch;
import com.crm.api.models.Company;
import com.crm.api.service.AddressService;
import com.crm.api.service.BranchService;
import com.crm.api.service.CompanyService;

@Service
@Configurable
public class BranchBusiness {
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private BranchService branchService;
	
	@Autowired
	private AddressService addressService;
	
	public Branch createBranch(Branch branch, Long companyId) {
		boolean checkFields = this.checkFields(branch);
		Company company = this.companyService.findById(companyId);
		branch = checkFields && company != null ?
				this.saveBranch(branch, company):
				null;
		return branch;
	}
	
	private boolean checkFields(Branch branch) {
		boolean checkBranch = this.checkBranchFields(branch);
		boolean checkAddress = this.checkAddressFields(branch);
		
		return checkAddress &&
			   checkBranch;
	}
	
	private boolean checkBranchFields(Branch branch) {
		return this.branchService.checkFields(branch);
	}
	
	private boolean checkAddressFields(Branch branch) {
		Address address = branch.getAddress();
		return this.addressService.checkFields(address);
	}
	
	private Branch saveBranch(Branch branch, Company company) {
		Address address = branch.getAddress();
		address = this.addressService.save(address);
		branch.setAddress(address);
		branch = this.branchService.save(branch);
		company.setBranch(branch);
		this.companyService.save(company);
		
		return branch;
	}
	
	public Branch saveBranch(Branch branch) {
		Address address = branch.getAddress();
		address = this.addressService.save(address);
		branch.setAddress(address);
		branch = this.branchService.save(branch);
		
		return branch;
	}

	public Branch updateBranch(Branch branch) {
		boolean checkFields = this.checkFields(branch);
		Branch branchDB = this.getDBBranch(branch);
		
		branch.setDeposits(branchDB.getDeposits());
		
		branch = this.saveBranch(branch);
		return branch;
	}
	
	private Branch getDBBranch(Branch branch) {
		Long id = branch.getId();
		Branch branchDB = this.branchService.findById(id);
		return branchDB;
	}

}
