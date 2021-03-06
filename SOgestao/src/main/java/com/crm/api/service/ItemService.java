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

	public Item save(Item item) {
		return this.itemRepository.save(item);
	}
	
	public Item update(Item item) {
		item = item.getId() != null ?
				this.save(item):
				null;
		
		return item;
	}
	
	public Item findById(long id) {
		Item item = this.itemRepository.findById(id);
		return item;
	}

	public boolean checkFields(Item item) {
		return item.getDescription() != null &&
			   item.getName() != null &&
			   item.getType() != null &&
			   item.getValidity() != null;
	}

	public void debitItem(Item item, int qtd) {
		qtd = item.getQtd() - qtd;
		item.setQtd(qtd);
		this.itemRepository.save(item);
	}
}
