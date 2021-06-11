package com.crm.api.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.ItemSale;
import com.crm.api.models.Movement;
import com.crm.api.models.Payment;
import com.crm.api.models.Sale;
import com.crm.api.service.PaymentService;
import com.crm.api.service.SaleService;

@Service
@Configurable
public class PaymentBusiness {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private MovementBusiness movementBusiness;
	
	public Sale paidOut(Payment payment) {
		Sale sale = this.saleService.getDBSale(payment);
		sale = sale != null ? 
				this.paid(sale, payment):
				null;
		return sale;
	}
	
	private Sale paid(Sale sale, Payment payment) {
		payment = this.paymentService.paidOut(payment);
		sale.setPayment(payment);
		sale = this.saleService.updatePaidOut(sale);
		
		List<ItemSale> items = sale.getItems();
		this.generateMovement(items);
		return sale;
	}
	
	private void generateMovement(List<ItemSale> items) {
		List<Movement> movements = this.movementBusiness
				.generateMovement(
						items, 
						"Output", 
						"Acquisition"
				);
		
		for(Movement movement : movements) {
			this.movementBusiness.saveMovement(movement);
		}
	}
}
