package com.crm.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Item;
import com.crm.api.models.ItemSale;
import com.crm.api.models.Sale;
import com.crm.api.models.Session;
import com.crm.api.repositories.ItemSaleRepository;
import com.crm.api.repositories.SaleRepository;
import com.crm.api.repositories.SessionRepository;

@Service
@Configurable
public class SaleService {
	
	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private ItemSaleRepository itemSaleRepository;
	
	private static String IN_PROGRESS = "In Progress";
	
	public Sale createSale(Sale sale) {
		Sale response = null;
		
		List<ItemSale> items = sale.getItems();
		sale.setStatus(this.IN_PROGRESS);
		
		boolean checkItems = this.checkItems(items);
		
		if(checkItems) {
			double totalSale = this.totalCalculate(items);
			sale.setTotal(totalSale);
			this.itemSaleRepository.saveAll(items);
			response = this.saleRepository.save(sale);
		}
		
		return response;
	}
	
	public Sale updateSale(Sale sale) {
		Session session = sale.getSession();
		session = this.getDBSession(session);
		String sessionStatus = session != null ? 
				session.getStatus():
				null;
		sale.setSession(session);
		
		Sale response = sessionStatus.contentEquals("active")?
				this.salesManager(sale):
				null;
		
		return response;
	}
	
	private Sale salesManager(Sale sale) {
		String status = sale.getStatus();
		return null;
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
	
	private Session getDBSession(Session session) {
		long id = session.getId();
		session = this.sessionService.getById(id);
		return session;
	}
}
