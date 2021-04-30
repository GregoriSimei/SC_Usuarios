package com.crm.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Branch;
import com.crm.api.models.Company;
import com.crm.api.models.Invoice;
import com.crm.api.models.Movement;
import com.crm.api.repositories.BranchRepository;
import com.crm.api.repositories.InvoiceRepository;

@Service
@Configurable
public class InvoiceService {
	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	private BranchRepository branchRepository;
	
	public Invoice saveInvoice(List<Movement> movs, long branchid ) {
		Invoice inv = null;
		Branch branch = branchRepository.findById(branchid);
		// inv.setBranch(branch);
		inv.setMovements(movs);
		Invoice i = invoiceRepository.save(inv);
		return i;
	}

}
