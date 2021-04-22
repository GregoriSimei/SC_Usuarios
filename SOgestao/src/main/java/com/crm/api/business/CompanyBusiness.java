package com.crm.api.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Company;
import com.crm.api.repositories.CompanyRepository;

@Service
@Configurable
public class CompanyBusiness {
	
	@Autowired
	private CompanyRepository companyRepository;

	public Company saveCompany(Company company) {
		Company response = companyRepository.save(company);
		return response;
	}
	
}
