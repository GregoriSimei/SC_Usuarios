package com.crm.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.models.Item;
import com.crm.api.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@PostMapping(consumes = "application/json", produces = "application/json")
	public Item postItemInDeposit(@RequestBody Item item,@RequestParam("id") long id) {
		item = this.itemService.save(item ,id);
		return item;
	}
	
	@GetMapping(value = "/branch" ,produces = "application/json")
	public List<Item> getAllbyBranch(@RequestParam("id") long id){
		List<Item> items = this.itemService.getByBranchId(id);
		return items;
	}
	
	@GetMapping(value = "/deposit", produces = "application/json")
	public List<Item> getAllbyDeposit(@RequestParam("id") long id){
		List<Item> items = this.itemService.getByDepositId(id);
		return items;
	}
	
	@GetMapping(produces = "application/json")
	public Item getItemById(@RequestParam("id") long id) {
		Item item = this.itemService.getItemById(id);
		return item;
	}
	
}
