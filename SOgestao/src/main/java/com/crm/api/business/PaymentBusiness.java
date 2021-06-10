package com.crm.api.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.ItemSale;
import com.crm.api.models.Payment;
import com.crm.api.models.Sale;
import com.crm.api.models.Session;
import com.crm.api.service.ItemSaleService;
import com.crm.api.service.PaymentService;
import com.crm.api.service.SaleService;
import com.crm.api.service.SessionService;

@Service
@Configurable
public class PaymentBusiness {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private ItemSaleService itemSaleService;
	
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
		this.debitToProduct(items);
		return sale;
	}
	
	private void debitToProduct(List<ItemSale> items) {
		this.itemSaleService.debitProduct(items);
	}
}
