package com.crm.api.user;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crm.api.controllers.UserRequestController;
import com.crm.api.models.Address;
import com.crm.api.models.Person;
import com.crm.api.models.User;
import com.crm.api.models.UserRequest;

@SpringBootTest
public class UserTest {
	
	@Autowired
	private UserRequestController ucontrol;
	
	@Test
	void salvarPessoa() {
		Person p = new Person();
		p.setName("Jubileu");
		p.setCpf("123456789");
		Date data = new Date();  
		java.sql.Date dataSql = new java.sql.Date(data.getTime());
		p.setBirth(dataSql);
		p.setCell("41 4002-8922");
		Address adr = new Address();
		adr.setStreet("Rua dos bobos");
		adr.setNumber("0");
		adr.setZipcode("83601-490");
		adr.setDistrict("Centro");
		adr.setCity("Campo Largo");
		p.setAddress(adr);
		UserRequest ureq = ucontrol.salvaUsuario(p);
		assertEquals(p, ureq.getPerson());
		
	}
	

}
