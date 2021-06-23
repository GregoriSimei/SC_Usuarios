package com.crm.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.crm.api.models.Deposit;

public interface DepositRepository extends CrudRepository<Deposit, Long>{

	Deposit findById(long id);
	Deposit findByItemsId(long id);
	
}
