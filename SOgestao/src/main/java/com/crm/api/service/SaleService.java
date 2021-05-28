package com.crm.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Item;
import com.crm.api.models.ItemSale;
import com.crm.api.models.Sale;

@Service
@Configurable
public class SaleService {

	@Autowired
	private ItemService itemService;
	
	public Sale createSale(Sale sale) {
		Sale response = null;
		
		List<ItemSale> items = sale.getItems();
		sale.setStatus("In Progress");
		
		boolean checkItemsExist = this.checkItemsExist(items);
		
		if(checkItemsExist) {
			double totalSale = this.totalCalculate(items);
			sale.setTotal(totalSale);
		}
		
		return response;
	}

	private boolean checkItemsExist(List<ItemSale> items) {
		boolean validation = false;
		
		for(ItemSale itemSale : items) {
			Item item = itemSale.getItem();
			long id = item.getId();
			
			Item itemToCheck = this.itemService.getItemById(id);
			
			if(itemToCheck != null) {
				validation = true;
			}
			else {
				validation = false;
				break;
			}
		}
		return validation;
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
