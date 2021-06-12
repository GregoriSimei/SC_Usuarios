package com.crm.api.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Address;
import com.crm.api.models.Person;
import com.crm.api.models.UserRequest;
import com.crm.api.service.AddressService;
import com.crm.api.service.PersonService;
import com.crm.api.service.UserRequestService;

@Service
@Configurable
public class UserRequestBusiness {
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private UserRequestService userRequestService;

	public UserRequest createRequest(UserRequest request) {
		boolean validation = this.checkRequest(request);
		
		request = validation? 
				this.saveRequest(request):
				null;
		
		return request;
	}
	
	private boolean checkRequest(UserRequest request) {
		boolean requestValidation  = this.userRequestService.checkFields(request);
		boolean personValidation = this.checkPerson(request);
		boolean addressValidation = this.checkAddress(request);
		
		return requestValidation &&
				personValidation &&
				addressValidation;
	}
	
	private boolean checkPerson(UserRequest request) {
		Person person = request.getPerson();
		boolean personValidation = this.personService.checkFields(person);
		return personValidation;
	}

	private boolean checkAddress(UserRequest request) {
		Person person = request.getPerson();
		Address address = person.getAddress();
		boolean addressValidation = this.addressService.checkFields(address);
		return addressValidation;
	}
	
	private UserRequest saveRequest(UserRequest request) {
		Person person = request.getPerson();
		Address address = person.getAddress();
		this.saveAddress(address);
		this.savePerson(person);
		return this.userRequestService.save(request);
	}
	
	private Address saveAddress(Address address) {
		return this.addressService.save(address);
	}
	
	private Person savePerson(Person person) {
		return this.personService.save(person);
	}
}
