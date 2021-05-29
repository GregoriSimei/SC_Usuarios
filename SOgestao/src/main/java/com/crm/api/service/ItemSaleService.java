package com.crm.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.ItemSale;
import com.crm.api.repositories.ItemSaleRepository;

@Service
@Configurable
public class ItemSaleService {
	
	@Autowired
	private ItemSaleRepository itemSaleRepository;
	
	public List<ItemSale> saveAll(List<ItemSale> items){
		items = this.saveAll(items);
		return items;
	}

}
