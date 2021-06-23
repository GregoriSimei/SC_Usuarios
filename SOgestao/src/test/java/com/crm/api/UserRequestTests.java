package com.crm.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crm.api.business.UserRequestBusiness;
import com.crm.api.models.Address;
import com.crm.api.models.Person;
import com.crm.api.models.User;
import com.crm.api.models.UserRequest;
import com.crm.api.repositories.AddressRepository;
import com.crm.api.repositories.PersonRepository;
import com.crm.api.repositories.UserRequestRepository;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class UserRequestTests {
	
	@Autowired
	private UserRequestBusiness userRequestBusiness;
	
	@Autowired
	private UserRequestRepository userRequestRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	private List<UserRequest> requests = new ArrayList<UserRequest>();
	
	@BeforeAll
	public void createDataBase() {
		Person person = this.getPerson();
		Address address = person.getAddress();
		
		address = this.addressRepository.save(address);
		this.personRepository.save(person);
		
		UserRequest request = new UserRequest();
		request.setCreation(new Date());
		request.setPerson(person);
		request.setStatus("Pending Approval");
		
		request = this.userRequestRepository.save(request);
		requests.add(request);
	}
	
	@Test
	public void createARequest() {
		UserRequest request = new UserRequest();
		request.setPerson(this.getPerson());
		request = this.userRequestBusiness.createRequest(request);
		requests.add(request);
		
		assertNotEquals(null, request);
		assertEquals("Pending Approval", request.getStatus());
	}
	
	@Test
	public void notIsPossibleToCreateARequestWithotAPerson() {
		UserRequest request = new UserRequest();
		request = this.userRequestBusiness.createRequest(request);
		
		assertEquals(null, request);
	}
	
	@Test
	public void approveRequestReturnAPersoWithAnActiveUser() {
		UserRequest request = requests.get(0);
		request.setStatus("Approved");
		
		request = this.userRequestBusiness.updateRequest(request);
		
		User user = request.getPerson().getUser();
		
		boolean userCreated = user != null;
		
		assertEquals(true, userCreated);
		assertEquals(true, user.getActive());
	}
	
	@Test
	public void notApproveRequestReturnAPersonWithotAnUserAndNotApprovedStatus() {
		UserRequest request = requests.get(0);
		request.setStatus("Not Approved");
		
		request = this.userRequestBusiness.updateRequest(request);
		
		User user = request.getPerson().getUser();
		
		boolean userCreated = user != null;
		
		assertEquals(false, userCreated);
	}
	
	@AfterAll
	public void clearDataBase() {
		for(UserRequest request: requests) {
			this.userRequestRepository.delete(request);
		}
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
