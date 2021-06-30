package com.crm.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Invoice;
import com.crm.api.repositories.InvoiceRepository;

@Service
@Configurable
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	public Invoice save(Invoice invoice) {
		return this.invoiceRepository.save(invoice);
	}
	
}
