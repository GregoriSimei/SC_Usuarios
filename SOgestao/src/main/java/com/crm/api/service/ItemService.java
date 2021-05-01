package com.crm.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Item;
import com.crm.api.repositories.ItemRepository;

@Service
@Configurable
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public Item getItemById(long id) {
		Item item = this.itemRepository.findById(id).get();
		return item;
	}
	
	
	
}
