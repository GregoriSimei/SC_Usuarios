package com.crm.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Item;
import com.crm.api.models.ItemSale;
import com.crm.api.models.Payment;
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
	private ItemSaleService itemSaleService;
	
	@Autowired
	private PaymentService paymentService;
	
	private final String IN_PROGRESS = "In Progress";
	private final String PAYMENT_PENDING = "Payment Pending";
	private final String PAID_OUT = "Paid Out";
	private final String CANCELED = "Canceled";
	
	
	public Sale createSale(Sale sale) {
		List<ItemSale> items = sale.getItems();
		sale.setStatus(this.IN_PROGRESS);
		
		boolean checkItems = this.checkItems(items);
		
		sale = checkItems ? 
				this.persistDataSale(sale):
				null;
		
		return sale;
	}
	
	public Sale updateSale(Sale sale) {
		Session session = sale.getSession();
		session = this.getDBSession(session);
		String sessionStatus = session != null ? 
				session.getStatus():
				null;
		sale.setSession(session);
		
		List<ItemSale> items = sale.getItems();
		
		Sale response = sessionStatus.contentEquals("Active")?
				(
					this.checkItems(items)?
						this.salesManager(sale):
						null
				):
				null;
		
		return response;
	}
	
	private Sale salesManager(Sale sale) {
		String status = sale.getStatus();
		
		if(status.contentEquals(this.IN_PROGRESS)) {
			sale = this.updateProgress(sale);
		}
		else if(status.contentEquals(this.PAYMENT_PENDING)) {
			sale = this.updatePaymentPending(sale);
		}
		
		return sale;
	}
	
	private Sale updateProgress(Sale sale) {
		sale = this.persistDataSale(sale);
		return sale;
	}
	
	private Sale updatePaymentPending(Sale sale) {
		List<ItemSale> items = sale.getItems();
		double totalSale = this.totalCalculate(items);
		sale.setTotal(totalSale);
		
		Payment payment = this.paymentService.save(sale);
		sale.setPayment(payment);
		
		Session session = sale.getSession();
		session = this.sessionService.finishSession(session);
		sale.setSession(session);
		
		sale = this.persistDataSale(sale);
		
		return sale;
	}
	
	private Sale persistDataSale(Sale sale) {
		List<ItemSale> items = sale.getItems();
		double totalSale = this.totalCalculate(items);
		sale.setTotal(totalSale);
		this.itemSaleService.saveAll(items);
		sale = this.saleRepository.save(sale);
		return sale;
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
			int qtd =  itemSale.getQtd();
			total += item.getPrice() * qtd;
		}
		
		return total;
	}
	
	private Session getDBSession(Session session) {
		long id = session.getId();
		session = this.sessionService.getById(id);
		return session;
	}
}
