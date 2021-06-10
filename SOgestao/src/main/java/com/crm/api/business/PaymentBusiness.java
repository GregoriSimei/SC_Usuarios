package com.crm.api.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Payment;
import com.crm.api.models.Sale;
import com.crm.api.models.Session;
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
	
	public Sale paidOut(Payment payment) {
		Sale sale = this.saleService.getDBSale(payment);
		sale = sale != null ? 
				this.paid(sale, payment):
				null;
		return null;
	}
	
	private Sale paid(Sale sale, Payment payment) {
		payment = this.paymentService.paidOut(payment);
		sale.setPayment(payment);
		sale = this.saleService.updatePaidOut(sale);
		return sale;
	}
}
