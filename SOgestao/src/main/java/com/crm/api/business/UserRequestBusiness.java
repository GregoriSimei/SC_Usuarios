package com.crm.api.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Address;
import com.crm.api.models.Person;
import com.crm.api.models.User;
import com.crm.api.models.UserRequest;
import com.crm.api.service.AddressService;
import com.crm.api.service.PersonService;
import com.crm.api.service.UserRequestService;
import com.crm.api.service.UserService;

@Service
@Configurable
public class UserRequestBusiness {
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRequestService userRequestService;

	public UserRequest createRequest(UserRequest request) {
		boolean validation = this.checkAllFilds(request);
		
		request = validation? 
				this.saveRequest(request):
				null;
		
		return request;
	}

	public UserRequest updateRequest(UserRequest request) {
		boolean validation = this.checkAllFilds(request);
		boolean checkStatus = this.checkStatus(request);
		request = validation && checkStatus ? 
				this.approvalOrNot(request):
				null;

		return request;
	}
	
	private UserRequest approvalOrNot(UserRequest request) {
		String status = request.getStatus();
		
		if(status.contentEquals("Approved")) {
			User user = this.generateUser(request);
			user = this.userService.save(user);
			request.getPerson().setUser(user);
			this.personService.save(request.getPerson());
			request = this.userRequestService.requestApproved(request);
		}
		else if(status.contentEquals("Not Approved")) {
			request = this.userRequestService.requestNotApproved(request);
		}
		
		return request;
	}

	private User generateUser(UserRequest request) {
		Person person = request.getPerson();
		person = this.personService.getPerson(person);
		
		String name = person.getName();
		Date birth = person.getBirth();
		
		return this.userService.createUser(name, birth);
	}

	private boolean checkAllFilds(UserRequest request) {
		boolean requestValidation  = this.checkRequestFilds(request);
		boolean personValidation = this.checkPersonFilds(request);
		boolean addressValidation = this.checkAddressFilds(request);
		
		return requestValidation &&
				personValidation &&
				addressValidation;
	}
	
	private boolean checkRequestFilds(UserRequest request) {
		return this.userRequestService
				.checkFields(request);
	}
	
	private boolean checkPersonFilds(UserRequest request) {
		Person person = request.getPerson();
		boolean personValidation = person != null ?
					this.personService.checkFields(person):
					false;
		return personValidation;
	}

	private boolean checkAddressFilds(UserRequest request) {
		Person person = request.getPerson();
		
		Address address = person != null ? 
					person.getAddress():
					null;
		
		boolean addressValidation = address != null ?
					this.addressService.checkFields(address):
					false;
		
		return addressValidation;
	}
	
	private boolean checkStatus(UserRequest request) {
		String status = request.getStatus();
		return this.userRequestService.checkStatus(status);
	}
	
	private UserRequest saveRequest(UserRequest request) {
		Person person = request.getPerson();
		person = this.savePerson(person);
		request.setPerson(person);
		return this.userRequestService.save(request);
	}
	
	private Address saveAddress(Address address) {
		return this.addressService.save(address);
	}
	
	public Person savePerson(Person person) {
		Address address = person.getAddress();
		this.saveAddress(address);
		person.setAddress(address);
		
		return this.personService.save(person);
	}

}
