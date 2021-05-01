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
	

	public Company saveCompany(Company company) {
		Company response = companyRepository.save(company);
		return response;
	}
	
	
}
