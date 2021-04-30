package com.crm.api.service;

import com.crm.api.models.Document;
import com.crm.api.models.Movement;

public class MovimentService {

	
	public Document DocumentGenerate(Movement movement) {
		Document document = new Document();
		
		String type = movement.getType();
		String subType = movement.getSubType();
		String documentName = "";
		
		if(type.equalsIgnoreCase("Incoming")) {
			switch (subType){
				case "Acquisition":
					documentName = "Purchase Note";
				case "Devolution":
					documentName = "Devolution Note";
				case "Transfer":
					documentName = "Transfer Note";
			}
		}
		else if(type.equalsIgnoreCase("Output")) {
			switch (subType){
				case "Acquisition":
					documentName = "Bill of Sale";
				case "Devolution":
					documentName = "Devolution Note";
				case "Transfer":
					documentName = "Transfer Note";
				case "Consumption":
					documentName = "Consumption Note";
				case "Theft":
					documentName = "Theft Note";
			}
		}
		
		document.setName(documentName);
		
		return document;
	}
	
}
