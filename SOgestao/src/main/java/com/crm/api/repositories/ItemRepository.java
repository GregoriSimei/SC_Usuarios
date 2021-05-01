package com.crm.api.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.crm.api.models.Item;

public interface ItemRepository extends CrudRepository<Item, Long>{
	
}
