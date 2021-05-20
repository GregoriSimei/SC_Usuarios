package com.crm.api.movement;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crm.api.controllers.DepositController;
import com.crm.api.models.Branch;
import com.crm.api.models.Deposit;
import com.crm.api.repositories.BranchRepository;

@SpringBootTest
public class DepositTest {
	@Autowired
	private DepositController depcontroll;
	@Autowired
	private BranchRepository branchRepository;
	
	@Test
	void saveDeposit() {
		Deposit dep = new Deposit();
		dep.setName("Estoque 1");
		Branch branch = branchRepository.findById(10);
		branch = depcontroll.postDeposit(dep, branch.getId());
		assertEquals(dep, branch.getDeposits().get(0));
		
	}

}
