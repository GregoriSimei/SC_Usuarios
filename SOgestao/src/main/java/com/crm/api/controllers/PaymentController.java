package com.crm.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.business.PaymentBusiness;
import com.crm.api.models.Item;
import com.crm.api.models.NoteMovement;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private PaymentBusiness paymentBusiness;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public NoteMovement create(@RequestBody NoteMovement movement, @RequestParam("id") Long id) {
		return this.paymentBusiness.createMovement(movement, id);
	}
	
}
