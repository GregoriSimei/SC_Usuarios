package com.crm.api.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Deposit;
import com.crm.api.models.Document;
import com.crm.api.models.Item;
import com.crm.api.models.Movement;
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

	public Movement createMovement(Movement movement) {
		boolean checkFields = this.checkFields(movement);
		boolean checkRelationship = checkFields ? 
				this.checkRelationship(movement):
				false;
		
		movement = checkRelationship ? 
				this.movementManager(movement):
				null;
		
		return movement;
	}
	
	private Movement movementManager(Movement movement) {
		Item item = this.updateItem(movement);
		Deposit deposit = this.updateDeposit(movement);
		
		return null;
	}
	
	private Item updateItem(Movement movement) {
		return null;
	}
	
	private Deposit updateDeposit(Movement movement) {
		return null;
	}
	
	private boolean checkRelationship(Movement movement) {
		Deposit deposit = movement.getDeposit();
		Item item = movement.getItem();
		
		boolean checkItemOnDeposit = checkItemOnDeposit(item, deposit);
		return checkItemOnDeposit;
	}

	private boolean checkItemOnDeposit(Item item, Deposit deposit) {
		item = this.getItem(item);
		deposit = this.getDeposit(deposit);
		
		for(Item itemDeposit : deposit.getItems()) {
			if(itemDeposit.getId() == item.getId()) {
				return true;
			}
		}
		
		return false;
	}
	
	private Deposit getDeposit(Deposit deposit) {
		Long id = deposit.getId();
		return this.depositService.findById(id);
	}
	
	private Item getItem(Item item) {
		Long id = item.getId();
		return this.itemService.findById(id);
	}

	private boolean checkFields(Movement movement) {
		boolean checkMovement = this.checkMovementFields(movement);
		boolean checkDeposit = this.checkDepositFields(movement);
		boolean checkItem = this.checkItemFields(movement);
		
		return checkMovement &&
			   checkDeposit &&
			   checkItem;
	}

	private boolean checkItemFields(Movement movement) {
		Item item = movement.getItem();
		return item != null? 
			item.getId() != null:
			false;
	}

	private boolean checkDepositFields(Movement movement) {
		Deposit deposit = movement.getDeposit();
		return deposit != null?
				deposit.getId() != null:
				false;
	}

	private boolean checkMovementFields(Movement movement) {
		return this.movementService.checkFields(movement);
	}
}
