package com.crm.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.models.Address;
import com.crm.api.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	public Address update(@RequestBody Address address) {
		address = this.addressService.save(address);
		return address;
	}

	@GetMapping(produces = "application/json")
	public Address getById(@RequestParam("id") Long id) {
		return this.addressService.findById(id);
	}

}
