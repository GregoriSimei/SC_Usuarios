package com.crm.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.crm.api.models.Deposit;
import com.crm.api.models.Item;

public interface ItemRepository extends CrudRepository<Item, Long>{
	Item findById(long id);
	Deposit findDepositById(long id);
}
