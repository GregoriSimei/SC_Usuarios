package com.crm.api.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Deposit;
import com.crm.api.models.Document;
import com.crm.api.models.Item;
import com.crm.api.models.Movement;
import com.crm.api.models.User;
import com.crm.api.repositories.DepositRepository;
import com.crm.api.repositories.MovementRepository;
import com.crm.api.service.DepositService;
import com.crm.api.service.ItemService;

@Service
@Configurable
public class MovementBusiness {
	
	@Autowired
	private MovementRepository movementRepository;
	
	@Autowired
	private DepositService depositService;
	
	@Autowired
	private ItemService itemService;
	
	public List<Movement> getAll(Long id) {
		List<Movement> movements = movementRepository.findByDepositId(id);
		return movements;
	}

	public Movement saveMovement(Movement movement) {
		
		Deposit deposit = movement.getDeposit();
		Item item = movement.getItem();
		User user = movement.getUser();
		
		boolean testDeposit = deposit.getId() != 0;
		if(testDeposit) {
			long id = deposit.getId();
			deposit = this.depositService.getById(id);
		}
		
		boolean testItem = item.getId() != 0;
		if(testItem) {
			item = this.adjustItem(movement);
		}
		
		
		
		return movement;
	}
	
	private Item adjustItem(Movement movement) {
		String type = movement.getType();
		int qtd = movement.getQtd();
		Item item = movement.getItem();
		
		item = this.itemService.getItemById(item.getId());
		
		if(type.equalsIgnoreCase("Incoming")) {
			
		}
		
		
		return null;
	}
	
}
