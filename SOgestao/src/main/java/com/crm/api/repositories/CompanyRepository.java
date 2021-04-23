package com.crm.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crm.api.models.Company;
import com.crm.api.models.User;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long>{
	
	Company findById(long id);

}
