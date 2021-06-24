package com.crm.api.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Item;
import com.crm.api.models.ItemSale;
import com.crm.api.models.Sale;
import com.crm.api.models.Session;
import com.crm.api.service.ItemSaleService;
import com.crm.api.service.ItemService;
import com.crm.api.service.SaleService;
import com.crm.api.service.SessionService;

@Service
@Configurable
public class SaleBusiness {
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemSaleService itemSaleService;
	
	private static String IN_PROGRESS = "In Progress";
	
	public Sale save(Sale sale) {
		sale = sale.getId() != null ?
				this.update(sale):
				this.create(sale);
		
		sale = sale != null?
			this.saveAll(sale):
			null;
		
		return sale;
	}

	private Sale saveAll(Sale sale) {
		sale = this.saveItems(sale);
		sale = this.saleService.save(sale);
		
		return sale;
	}

	private Sale saveItems(Sale sale) {
		List<ItemSale> items = sale.getItems();
		items = this.itemSaleService.saveAll(items);
		sale.setItems(items);
		return sale;
	}

	private Sale create(Sale sale) {
		boolean checkFields = this.checkFields(sale);
		boolean checkItems = checkFields ? this.checkitems(sale): false;
		
		sale = checkItems ?
				this.generateSale(sale):
				null;
		
		return sale;
	}

	private boolean checkitems(Sale sale) {
		List<ItemSale> items = sale.getItems();
		
		for(ItemSale item: items) {
			Long id = item.getItem().getId();
			Item itemDB = this.itemService.findById(id);
			
			if(itemDB == null) {
				return false;
			}
		}
		
		return true;
	}

	private Sale generateSale(Sale sale) {
		sale = this.setItems(sale);
		Session session = this.generateSession();
		sale.setSession(session);
		sale.setStatus(IN_PROGRESS);
		sale.setDate(new Date());
		sale = this.calculateTotal(sale);
		
		return sale;
	}

	private Sale setItems(Sale sale) {
		List<ItemSale> items = sale.getItems();
		
		for(ItemSale item: items) {
			Long id = item.getItem().getId();
			item.setItem(
					this.itemService.findById(id)
				);
		}
		
		sale.setItems(items);
		
		return sale;
	}

	private Sale calculateTotal(Sale sale) {
		List<ItemSale> items = sale.getItems();
		
		double value = 0.0;
		
		for(ItemSale item : items) {
			value += item.getItem().getPrice() * item.getQtd();
		}
		
		sale.setTotal(value);
		
		return sale;
	}

	private Session generateSession() {
		return this.sessionService.generate();
	}

	private boolean checkFields(Sale sale) {
		boolean checkSaleFields = this.checkSaleFields(sale);
		boolean checkItemsFields = this.checkItemsFields(sale);
		
		return checkSaleFields &&
			   checkItemsFields;
	}

	private boolean checkItemsFields(Sale sale) {
		boolean validation = true;
		
		List<ItemSale> items = sale.getItems();
		
		for(ItemSale item : items) {
			Item itemTest = item.getItem();
			validation = itemTest != null;
			validation = validation ?
					itemTest.getId() != null:
					false;
			
			if(!validation) {
				break;
			}
		}
		
		return validation;
	}

	private boolean checkSaleFields(Sale sale) {
		return this.saleService.checkFields(sale);
	}

	private Sale update(Sale sale) {
		return null;
	}
}
