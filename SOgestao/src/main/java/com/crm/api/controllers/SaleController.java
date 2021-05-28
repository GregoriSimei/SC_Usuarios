package com.crm.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.business.SaleBusiness;
import com.crm.api.models.Sale;

@RestController
@RequestMapping("/sale")
public class SaleController {

	@Autowired
	private SaleBusiness saleBusiness;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Sale createSale(@RequestBody Sale sale) {
		Sale response = this.saleBusiness.createSale(sale);
		return response;
	}
}
