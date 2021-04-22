package com.crm.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crm.api.models.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long>{

}
