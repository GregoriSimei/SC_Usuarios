package com.crm.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crm.api.models.Branch;


@Repository
public interface BranchRepository extends CrudRepository<Branch, Long>{

	Branch findById(long id);
	Branch deleteById(long id);
}
