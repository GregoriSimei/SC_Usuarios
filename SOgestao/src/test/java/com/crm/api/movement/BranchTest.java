package com.crm.api.movement;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crm.api.controllers.BranchController;
import com.crm.api.controllers.CompanyController;
import com.crm.api.models.Address;
import com.crm.api.models.Branch;
import com.crm.api.models.Company;
import com.crm.api.repositories.CompanyRepository;

@SpringBootTest
public class BranchTest {
	@Autowired
	private BranchController branchcontroll;
	@Autowired
	private CompanyRepository companyRepository;
	
	@Test
	void saveBranch() {
		Branch b= new Branch();
		b.setName("Rainha do salgado");
		Address adr = new Address();
		adr.setStreet("Rua dos comerciantes");
		adr.setNumber("14");
		adr.setZipcode("83601-490");
		adr.setDistrict("Centro");
		adr.setCity("Curitiba");
		b.setAddress(adr);
		Company comp = companyRepository.findById(8);
		comp = branchcontroll.putCompany(b, comp.getId());
		assertEquals(b, comp.getBranches().get(0));
	}

}
