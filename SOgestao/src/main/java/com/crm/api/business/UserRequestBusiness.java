package com.crm.api.business;

import java.util.Date;

import com.crm.api.models.Person;
import com.crm.api.models.UserRequest;

public class UserRequestBusiness {
	
	public UserRequest createRequest(Person person) {
		
		UserRequest response = null;
		
		boolean check = this.checkPerson(person);
		
		if (check) {
			response = new UserRequest();
			response.setPerson(person);
			response.setStatus("pending approval");
			response.setCreation(new Date());
		}
		
		return response;
	}
	
	private boolean checkPerson(Person person) {
		boolean checkCPF = this.checkCPF(person.getCpf());
		boolean checkBith = this.checkBirthDate(person.getBirth());
		return checkCPF && checkBith;
	}
	
	private boolean checkCPF(String CPF) {
		boolean check = CPF.isBlank();
		return !check;
	}
	
	private boolean checkBirthDate(Date date) {
		
		boolean check = false;
		Date now = new Date();
		
		int tolerance = 200;
		int adjust = 1900;
		
		int toleranceYear = now.getYear() + adjust - tolerance;
		int birthDate = date.getYear() + adjust;
		
		if(birthDate > toleranceYear) {
			check = true;
		}
		
		return check;
	}

}
