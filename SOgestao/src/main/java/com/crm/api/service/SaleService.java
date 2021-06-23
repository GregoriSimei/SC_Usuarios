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
	
	private final String IN_PROGRESS = "In Progress";
	private final String PAYMENT_PENDING = "Payment Pending";
	private final String PAID_OUT = "Paid Out";
	private final String CANCELED = "Canceled";
	
	public Sale save(Sale sale) {
		sale = sale.getId() != null?
			this.create(sale):
			this.update(sale);
		
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
			   status.contentEquals(CANCELED);
	}
	
}
