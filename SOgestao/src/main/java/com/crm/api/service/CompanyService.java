package com.crm.api.service;

import java.util.List;

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
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	

	public Company saveCompany(Company company) {
		Company response = companyRepository.save(company);
		return response;
	}
	
	public Company setBranchesById(Branch branch,long id) {
		Company response = companyRepository.findById(id);
		System.out.println(response);
		addressRepository.save(branch.getAddress());
		branchRepository.save(branch);
		response.setBranch(branch);
		companyRepository.save(response);
		return response;
	}
	
	public Branch updateBranch(Branch branch) {
		Branch bra = branchRepository.findById(branch.getId());
		bra.setName(branch.getName());
		branchRepository.save(bra);
		return bra;
		
	}
	
	public Branch deleteBranch(Branch branch) {
		return branchRepository.deleteById(branch.getId());
		
	}
	
}