package com.crm.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Branch;
import com.crm.api.models.Deposit;
import com.crm.api.models.Item;
import com.crm.api.repositories.BranchRepository;
import com.crm.api.repositories.DepositRepository;
import com.crm.api.repositories.ItemRepository;

@Service
@Configurable
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private DepositRepository depositRepository;

	public Item getItemById(long id) {
		Item item = this.itemRepository.findById(id);
		return item;
	}

	public Item save(Item item) {		
		item = this.itemRepository.save(item);
		return item;
	}

	public List<Item> getByBranchId(long id) {
		Branch branch = this.branchRepository.findById(id);
		List<Item> items = new ArrayList<Item>();
		
		for(Deposit deposit : branch.getDeposits()) {
			List<Item> itemsDeposit = deposit.getItems();
			items.addAll(itemsDeposit);
		}
		
		
		return items;
	}
	
	public List<Item> getByDepositId(long id) {
		Deposit deposit = this.depositRepository.findById(id);
		List<Item> items = deposit.getItems();
		return items;
	}

	public Item save(Item item, long id) {
		Deposit deposit = this.depositRepository.findById(id);
		item = this.itemRepository.save(item);
		deposit.setItem(item);
		this.depositRepository.save(deposit);
		return item;
	}

	public void debitItem(Item item, int qtd) {
		qtd = item.getQtd() - qtd;
		item.setQtd(qtd);
		this.itemRepository.save(item);
	}

	public Deposit getDeposit(long id) {
		Deposit deposit = this.depositRepository.findById(id);
		return deposit;
	}
}
