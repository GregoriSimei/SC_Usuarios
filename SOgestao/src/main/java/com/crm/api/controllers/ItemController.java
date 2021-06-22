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

import com.crm.api.business.ItemBusiness;
import com.crm.api.models.Item;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemBusiness itemBusiness;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Item create(@RequestBody Item item,@RequestParam("id") Long depositId) {
		return this.itemBusiness.createItem(item, depositId);
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public Item update(Item object) {
		// TODO Auto-generated method stub
		return null;
	}

	@GetMapping(produces = "application/json")
	public Item getById(Item id) {
		// TODO Auto-generated method stub
		return null;
	}

	@DeleteMapping(consumes = "application/json", produces = "application/json")
	public boolean delete(Item object) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
