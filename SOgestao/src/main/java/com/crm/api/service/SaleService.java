package com.crm.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Item;
import com.crm.api.models.ItemSale;
import com.crm.api.models.Sale;
import com.crm.api.repositories.SaleRepository;

@Service
@Configurable
public class SaleService {
	
	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private ItemService itemService;
	
	public Sale createSale(Sale sale) {
		Sale response = null;
		
		List<ItemSale> items = sale.getItems();
		sale.setStatus("In Progress");
		
		boolean checkItems = this.checkItems(items);
		
		if(checkItems) {
			double totalSale = this.totalCalculate(items);
			sale.setTotal(totalSale);
			response = this.saleRepository.save(sale);
		}
		
		return response;
	}

	private boolean checkItems(List<ItemSale> items) {
		boolean validation = false;
		
		for(ItemSale itemSale : items) {
			Item item = itemSale.getItem();
			item = this.getItemDB(item);
			
			if(item != null) {
				validation = true;
			}
			else {
				validation = false;
				break;
			}
		}
		
		return validation;
	}

	private Item getItemDB(Item item) {
		long id = item.getId();
		item = this.itemService.getItemById(id);
		return item;
	}

	private double totalCalculate(List<ItemSale> items) {
		double total = 0;
		
		for(ItemSale itemSale : items) {
			Item item = itemSale.getItem();
			total += item.getPrice();
		}
		
		return total;
	}
}
