package com.crm.api.movement;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crm.api.controllers.CompanyController;
import com.crm.api.models.Company;

@SpringBootTest
public class CompanyTest {
	@Autowired
	private CompanyController compcontroll;
	
	@Test
	void savecompany() {
		Company comp = new Company();
		comp.setName("Rei do espetinho");
		assertEquals(comp, compcontroll.postCompany(comp));
	}

}
