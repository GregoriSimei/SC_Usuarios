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

	private static String INCOMING = "Incoming";
	private static String OUTPUT = "Output";
	
	public Movement createMovement(Movement movement) {
		boolean checkFields = this.checkFields(movement);
		boolean checkType = this.checkType(movement);
		
		boolean checkDocument = checkType && checkFields ? 
				this.checkDocument(movement):
				false;
		
		boolean checkRelationship = checkFields ? 
				this.checkRelationship(movement):
				false;
	
		movement = checkRelationship && checkDocument? 
				this.movementManager(movement):
				null;
		
		return movement;
	}

	private Movement movementManager(Movement movement) {
		Item item = this.updateItem(movement);
		movement.setItem(item);
		Deposit deposit = this.updateDeposit(movement);
		movement.setDeposit(deposit);
		
		return this.saveMovement(movement);
	}
	
	private Movement saveMovement(Movement movement) {
		Item item = movement.getItem();
		Deposit deposit = movement.getDeposit();
		Document document = movement.getDoc();
		
		document = this.documentService.save(document);
		
		item = this.itemService.save(item);
		
		deposit = this.calculateDepositValue(deposit);
		deposit = this.depositService.save(deposit);
		
		movement.setItem(item);
		movement.setDeposit(deposit);
		movement.setDoc(document);
		movement = this.movementService.save(movement);
		
		return movement;
	}

	private Item updateItem(Movement movement) {
		Item item = movement.getItem();
		Item itemDB  = this.getItem(item);
		
		int qtd = this.qtdToMovement(movement);
		
		if(qtd > 0) {
			double value = item.getPrice();
			double valueDB = itemDB.getPrice();
			double avg = (value + valueDB)/2;
			
			itemDB.setPrice(value);
			itemDB.setAvgPrice(avg);
		}
		
		qtd = itemDB.getQtd() + qtd;
		itemDB.setQtd(qtd);
		
		return itemDB;
	}
	
	private int qtdToMovement(Movement movement) {
		return movement.getType()
				.contentEquals(INCOMING) ? 
						movement.getQtd():
						-movement.getQtd();
	}
	
	private Deposit updateDeposit(Movement movement) {
		Deposit deposit = this.getDeposit(movement.getDeposit());
		return deposit;
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
	
	private boolean checkDocument(Movement movement) {
		Document document = movement.getDoc();
		boolean checkDoc = this.checkDocTitle(document, movement.getType());
		return checkDoc;
	}
	
	private boolean checkDocTitle(Document document, String type) {
		boolean check = false;
		
		String[] incoming = new String[] {
					"Purchase Note",
					"Devolution Note",
					"Transfer Note",
					"Adjust"
				};
		String[] output = new String[] {
					"Bill of Sale",
					"Devolution Note",
					"Transfer Note",
					"Consumption Note",
					"Theft Note",
					"adjust"
				};
		
		String[][] titles = new String[][] {incoming,output};
		
		int titlesNum = type.contentEquals(INCOMING) ? 0 : 1;
		
		for(String title : titles[titlesNum]) {
			if(document.getTitle().contentEquals(title)) {
				check = true;
				break;
			}
		}
		
		return check;
	}

	private boolean checkType(Movement movement) {
		return movement.getType().contentEquals(INCOMING) ||
			   movement.getType().contentEquals(OUTPUT);
	}
	
	private Deposit calculateDepositValue(Deposit deposit) {
		double value = 0.0;
		
		for(Item item : deposit.getItems()) {
			value += (item.getPrice() * item.getQtd());
		}
		
		deposit.setTotalValue(value);

		return deposit;
	}
}
