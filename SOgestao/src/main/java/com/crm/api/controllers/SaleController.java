package com.crm.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.business.SaleBusiness;
import com.crm.api.models.Sale;
import com.crm.api.service.SaleService;

@RestController
@RequestMapping("/sale")
public class SaleController {

	@Autowired
	private SaleBusiness saleBusiness;
	
	@Autowired
	private SaleService saleService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Sale createSale(@RequestBody Sale sale) {
		Sale response = this.saleBusiness.createSale(sale);
		return response;
	}
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	public Sale updateSale(@RequestBody Sale sale) {
		sale = this.saleService.updateSale(sale);
		return sale;
	}
}
