package com.crm.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.crm.api.models.Item;

public interface ItemRepository extends CrudRepository<Item, Long>{

}
