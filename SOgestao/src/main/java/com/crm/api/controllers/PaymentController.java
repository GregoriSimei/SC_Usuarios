package com.crm.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.business.PaymentBusiness;
import com.crm.api.models.Payment;
import com.crm.api.models.Sale;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentBusiness paymentBusiness;
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	public Sale paidOut(@RequestBody Payment payment) {
		Sale sale = this.paymentBusiness.paidOut(payment);
		return sale;
	}
	
}
