package com.crm.api.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Deposit;
import com.crm.api.models.Document;
import com.crm.api.models.Item;
import com.crm.api.models.ItemSale;
import com.crm.api.models.Movement;
import com.crm.api.repositories.MovementRepository;
import com.crm.api.service.DepositService;
import com.crm.api.service.DocumentService;
import com.crm.api.service.ItemService;
import com.crm.api.service.MovementService;

@Service
@Configurable
public class MovementBusiness {
	
	@Autowired
	private DepositService depositService;
	
	@Autowired
	private MovementService movementService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private DocumentService documentService;

	public Movement saveMovement(Movement movement) {
		
		Deposit deposit = movement.getDeposit();
		Item item = movement.getItem();
		Document document = this.movementService.DocumentGenerate(movement);
		
		boolean testDeposit = deposit.getId() != 0;
		if(testDeposit) {
			long id = deposit.getId();
			deposit = this.depositService.getById(id);
		}
		
		boolean testItem = item.getId() != 0;
		if(testItem) {
			item = this.itemService.getItemById(item.getId());
		}
		
		movement.setItem(item);
		item = this.adjustItem(movement);
		
		this.documentService.save(document);
		item = this.itemService.save(item);
		
		deposit.setItem(item);
		
		movement.setDoc(document);
		movement.setDeposit(deposit);
		movement = this.movementService.save(movement);
		
		return movement;
	}
	
	private Item adjustItem(Movement movement) {
		String type = movement.getType();
		int qtd = movement.getQtd();
		Item item = movement.getItem();
		int qtdItem = item.getQtd();
		
		switch (type){
			case "Incoming":
				qtdItem += qtd;
				break;
			case "Output":
				qtdItem -= qtd;
				break;
		}
		
		item.setQtd(qtdItem);
		
		return item;
	}
	
	public List<Movement> generateMovement(List<ItemSale> items, String TYPE, String subType) {
		List<Movement> movements = new ArrayList<Movement>();
		
		for (ItemSale itemSale: items) {
			Item item = itemSale.getItem();
			int qtd = itemSale.getQtd();
			
			Movement movement = new Movement();
			movement.setItem(item);
			movement.setQtd(qtd);
			movement.setType(TYPE);
			movement.setSubType(subType);
			movement.setDeposit(this.getDepositByItemId(item));
			
			movements.add(movement);
		}
		return movements;
	}
	
	private Deposit getDepositByItemId(Item item) {
		long id = item.getId();
		Deposit deposit = this.itemService.getDeposit(id);
		return deposit;
	}
}
