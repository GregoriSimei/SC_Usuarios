package com.crm.api.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Sale;
import com.crm.api.models.Session;
import com.crm.api.service.SaleService;
import com.crm.api.service.SessionService;

@Service
@Configurable
public class SaleBusiness {

	@Autowired
	private SaleService saleService;
	
	@Autowired
	private SessionService sessionService;

	public Sale createSale(Sale sale) {
		Session session = this.sessionService.generateSession();
		sale.setSession(session);
		
		sale = this.saleService.createSale(sale);
				
		return sale;
	}
	
}
