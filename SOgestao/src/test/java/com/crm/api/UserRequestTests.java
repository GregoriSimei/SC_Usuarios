package com.crm.api;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crm.api.business.UserRequestBusiness;
import com.crm.api.models.Address;
import com.crm.api.models.Person;
import com.crm.api.models.UserRequest;
import com.crm.api.service.UserRequestService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class UserRequestTests {
	
	@Autowired
	private UserRequestBusiness userRequestBusiness;
	
	@Autowired
	private UserRequestService UserRequestService;
	
	private List<UserRequest> requests = new ArrayList<UserRequest>();
	
	@Test
	public void createARequest() {
		UserRequest request = new UserRequest();
		request.setPerson(this.getPerson());
		request = this.userRequestBusiness.saveRequest(request);
		requests.add(request);
		
		assertNotEquals(null, request);
	}
	
	@Test
	public void notIsPossibleToCreateARequestWithotAPerson() {
		UserRequest request = new UserRequest();
		request = this.userRequestBusiness.saveRequest(request);
		requests.add(request);
		
		assertNotEquals(null, request);
	}
	
	private Person getPerson() {
		Person person = new Person();
		person.setBirth(new Date());
		person.setCell("41321654987");
		person.setCpf("12345678912");
		person.setName("Gregori Simei");
		person.setAddress(this.getAddress());
		return person;
	}
	
	private Address getAddress() {
		Address address = new Address();
		address.setCity("Curitiba");
		address.setDistrict("Xaxim");
		address.setNumber("123");
		address.setStreet("Rua dos Bobos");
		address.setZipcode("12345678");
		
		return address;
	}
	
}
