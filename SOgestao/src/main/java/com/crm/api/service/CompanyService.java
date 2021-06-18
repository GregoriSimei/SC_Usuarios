package com.crm.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Company;
import com.crm.api.repositories.CompanyRepository;

@Service
@Configurable
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	

	public Company save(Company company) {
		company = company.getId() != null?
				this.update(company):
				this.create(company);
		
		company = companyRepository.save(company);
		return company;
	}
	
	private Company update(Company company) {
		return company;
	}
	
	private Company create(Company company) {
		company.setActive(true);
		return company;
	}
	
	public Company delete(Company company) {
		company.setActive(false);
		return company;
	}
	
	public boolean checkFields(Company company) {
		return company.getName() != null;
	}
	
}
