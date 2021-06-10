package com.crm.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Document;
import com.crm.api.models.Item;
import com.crm.api.models.ItemSale;
import com.crm.api.models.Movement;
import com.crm.api.repositories.MovementRepository;

@Service
@Configurable
public class MovementService {

	@Autowired
	private MovementRepository movementRepository;
	
	public List<Movement> getAll(Long id) {
		List<Movement> movements = movementRepository.findByDepositId(id);
		return movements;
	}
	
	public Document DocumentGenerate(Movement movement) {
		Document document = new Document();
		
		String type = movement.getType();
		String subType = movement.getSubType();
		String documentName = "";
		
		if(type.equalsIgnoreCase("Incoming")) {
			switch (subType){
				case "Acquisition":
					documentName = "Purchase Note";
					break;
				case "Devolution":
					documentName = "Devolution Note";
					break;
				case "Transfer":
					documentName = "Transfer Note";
					break;
			}
		}
		else if(type.equalsIgnoreCase("Output")) {
			switch (subType){
				case "Acquisition":
					documentName = "Bill of Sale";
					break;
				case "Devolution":
					documentName = "Devolution Note";
					break;
				case "Transfer":
					documentName = "Transfer Note";
					break;
				case "Consumption":
					documentName = "Consumption Note";
					break;
				case "Theft":
					documentName = "Theft Note";
					break;
			}
		}
		
		document.setName(documentName);
		
		return document;
	}

	public Movement save(Movement movement) {
		this.movementRepository.save(movement);
		return movement;
	}
}
