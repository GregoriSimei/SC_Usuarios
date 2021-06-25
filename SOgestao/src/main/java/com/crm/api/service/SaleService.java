package com.crm.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Sale;
import com.crm.api.repositories.SaleRepository;

@Service
@Configurable
public class SaleService {
	
	@Autowired
	private SaleRepository saleRepository;
	
	private static String IN_PROGRESS = "In Progress";
	private static String PAYMENT_PENDING = "Payment Pending";
	private static String CANCELED = "Canceled";
	private static String PAID_OUT = "Paid Out";
	private static String DELIVERY_PENDING = "Delivery Pending";
	private static String DELIVERED = "Delivered";
	private static String DEVOLUTION = "Devolution";
	
	public Sale save(Sale sale) {
		sale = sale.getId() != null?
			this.update(sale):
			this.create(sale);
		
		return this.saleRepository.save(sale);
	}
	
	public Sale findById(Long id) {
		return this.saleRepository.findById(id).get();
	}
	
	private Sale create(Sale sale) {
		sale.setStatus(IN_PROGRESS);
		return sale;
	}
	
	private Sale update(Sale sale) {
		return this.checkStatus(sale)?
				sale:
				null;
	}
	
	private boolean checkStatus(Sale sale) {
		String status = sale.getStatus();
		return status.contentEquals(IN_PROGRESS) ||
			   status.contentEquals(PAYMENT_PENDING) ||
			   status.contentEquals(PAID_OUT) ||
			   status.contentEquals(CANCELED) || 
			   status.contentEquals(DELIVERY_PENDING) ||
			   status.contentEquals(DELIVERED);
	}

	public boolean checkFields(Sale sale) {
		return sale.getItems() != null &&
			   sale.getClient() != null &&
			   sale.getUser() != null;
	}

	public Sale findByNoteId(Long noteId) {
		return this.saleRepository.findByNoteId(noteId);
	}
	
}
