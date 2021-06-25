package com.crm.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Sale create(@RequestBody Sale sale) {
		return this.saleBusiness.save(sale);
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public Sale update(@RequestBody Sale sale) {
		return this.saleBusiness.save(sale);
	}

	@GetMapping(produces = "application/json")
	public Sale getById(@RequestParam("id") Long id) {
		return this.saleService.findById(id);
	}

	@DeleteMapping(consumes = "application/json", produces = "application/json")
	public boolean delete(Sale object) {
		// TODO Auto-generated method stub
		return false;
	}
}
