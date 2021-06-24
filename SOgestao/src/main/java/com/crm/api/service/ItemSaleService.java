package com.crm.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Item;
import com.crm.api.models.ItemSale;
import com.crm.api.repositories.ItemSaleRepository;

@Service
@Configurable
public class ItemSaleService {
	
	@Autowired
	private ItemSaleRepository itemSaleRepository;
	
	@Autowired
	private ItemService itemService;
	
	public List<ItemSale> saveAll(List<ItemSale> items){
		items = (List<ItemSale>) this.itemSaleRepository.saveAll(items);
		return items;
	}

	public void debitProduct(List<ItemSale> items) {
		for(ItemSale itemSale : items) {
			int qtd = itemSale.getQtd();
			Item item = itemSale.getItem();
			this.itemService.debitItem(item, qtd);
		}
	}
}
