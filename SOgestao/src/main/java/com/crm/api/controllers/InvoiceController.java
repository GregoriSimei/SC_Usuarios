package com.crm.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.business.CompanyBusiness;
import com.crm.api.business.InvoiceBusiness;
import com.crm.api.models.Branch;
import com.crm.api.models.Deposit;
import com.crm.api.models.Invoice;
import com.crm.api.models.Movement;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
	@Autowired
	private InvoiceBusiness invoiceBusiness;
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Invoice postInvoice(@RequestBody List<Movement> movs,
			@RequestParam ("id") long branchid) {
		return invoiceBusiness.saveInvoice(movs, branchid);
	}

}
