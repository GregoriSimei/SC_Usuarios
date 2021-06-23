package com.crm.api.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Payment;
import com.crm.api.models.Sale;
import com.crm.api.repositories.PaymentRepository;

@Service
@Configurable
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	private final String PAYMENT_PENDING = "Payment Pending";
	private final String PAID = "Paid Out";
	
	public Payment save(Payment payment) {
		payment = this.paymentRepository.save(payment);
		return payment;
	}
	
	public Payment save(Sale sale) {
		Payment payment = new Payment();
		
		payment.setDate(new Date());
		payment.setStatus(this.PAYMENT_PENDING);
		payment.setValue(sale.getTotal());
		
		payment = this.save(payment);
		
		return payment;
	}

	public Payment paidOut(Payment payment) {
		payment = this.paymentDB(payment);
		payment = payment != null?
					this.paid(payment):
					payment;
					
		return payment;
	}
	
	private Payment paid(Payment payment) {
		payment.setStatus(PAID);
		payment = this.paymentPersist(payment);
		return payment;
	}
	
	private Payment paymentPersist(Payment payment) {
		payment = this.paymentRepository.save(payment);
		return payment;
	}
	
	private Payment paymentDB(Payment payment) {
		long id = payment.getId();
		payment = this.paymentRepository.findById(id).get();
		return payment;
	}
}
