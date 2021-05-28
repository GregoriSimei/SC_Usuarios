package com.crm.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.ItemSale;
import com.crm.api.models.Sale;

@Service
@Configurable
public class SaleService {

	@Autowired
	private ItemService itemService;
	
	public Sale createSale(Sale sale) {
		List<ItemSale> items = sale.getItems();
		
		boolean checkItems = this.checkItems(items); 
		//double totalSale = this.totalCalculate(items);
		//sale.setTotal(totalSale);
		return null;
	}

	private boolean checkItems(List<ItemSale> items) {
		for(ItemSale item : items) {
			
		}
		return true;
	}
}
