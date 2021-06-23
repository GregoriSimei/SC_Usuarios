package com.crm.api.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Deposit;
import com.crm.api.models.Item;
import com.crm.api.service.DepositService;
import com.crm.api.service.ItemService;

@Service
@Configurable
public class ItemBusiness {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private DepositService depositService;
	
	public Item createItem(Item item, Long id) {
		boolean checkFields = this.checkFields(item);
		boolean checkDeposit = this.checkDeposit(id);
		
		item = checkFields && checkDeposit ? 
				this.saveAll(item, id):
				null;
		
		return item;
	}
	
	private Item saveAll(Item item, Long id) {
		item = this.saveItem(item);
		Deposit deposit = this.getDepositById(id);
		deposit.setItem(item);
		this.saveDeposit(deposit);
		
		return item;
	}

	private Deposit saveDeposit(Deposit deposit) {
		return this.depositService.save(deposit);
	}

	private Item saveItem(Item item) {
		item.setPrice(this.setPrice(item));
		item.setAvgPrice(this.setAvgPrice(item));
	
		return this.itemService.save(item);
	}

	private double setAvgPrice(Item item) {
		double value = item.getPrice() != 0.0 ?
				(item.getPrice()+item.getAvgPrice())/2 :
				0.0;
		return value;
	}

	private double setPrice(Item item) {
		double value = item.getPrice() != 0.0 ?
				item.getPrice():
				0.0;
		return value;
	}

	private boolean checkDeposit(Long id) {
		Deposit deposit = this.depositService.findById(id);
		return deposit != null;
	}

	private boolean checkFields(Item item) {
		boolean checkItem = this.checkItemFields(item);
		return checkItem;
	}

	private boolean checkItemFields(Item item) {
		return this.itemService.checkFields(item);
	}

	private Deposit getDepositById(Long id) {
		return this.depositService.getById(id);
	}
	
}
