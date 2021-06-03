package com.crm.api.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Payment;
import com.crm.api.models.Sale;
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
	private SessionService sessionService;
	
	public Sale paidOut(Payment payment) {
		payment = this.paymentService.paidOut(payment);
		return null;
	}
}
