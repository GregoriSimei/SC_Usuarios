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
import com.crm.api.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemBusiness itemBusiness;
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Item create(@RequestBody Item item,@RequestParam("id") Long depositId) {
		return this.itemBusiness.createItem(item, depositId);
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public Item update(@RequestBody Item item) {
		return this.itemService.update(item);
	}

	@GetMapping(produces = "application/json")
	public Item getById(@RequestParam("id") Long id) {
		return this.itemService.findById(id);
	}

	@DeleteMapping(consumes = "application/json", produces = "application/json")
	public boolean delete(Item object) {
		return false;
	}

}
