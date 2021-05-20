package com.crm.api.movement;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crm.api.controllers.BranchController;
import com.crm.api.controllers.CompanyController;
import com.crm.api.controllers.DepositController;
import com.crm.api.controllers.MovementController;
import com.crm.api.controllers.UserRequestController;
import com.crm.api.models.Address;
import com.crm.api.models.Branch;
import com.crm.api.models.Company;
import com.crm.api.models.Deposit;
import com.crm.api.models.Document;
import com.crm.api.models.Item;
import com.crm.api.models.Movement;
import com.crm.api.models.Person;
import com.crm.api.repositories.DepositRepository;
import com.crm.api.repositories.ItemRepository;
import com.crm.api.repositories.UserRepository;
import com.crm.api.service.CompanyService;

@SpringBootTest
public class MovementTest {
	
	@Autowired
	private CompanyController compcontroll;
	@Autowired
	private BranchController branchcontroll;
	@Autowired
	private DepositController depcontroll;
	@Autowired
	private MovementController movcontroll;
	@Autowired
	private UserRequestController ucontrol;
	@Autowired
	private UserRepository uRepository;
	@Autowired
	private DepositRepository depRepository;
	@Autowired
	private ItemRepository itemRepository;
	
	@Test
	void EntradaEstoque() {	
		
		Movement mov = new Movement();	
		mov.setUser(uRepository.findByUsername("J2021"));
		mov.setDeposit(depRepository.findById(11));
		
		Document doc = new Document();
		doc.setContent("Tudo o que aconteceu na movimentaçao.txt");
		doc.setName("Log movimentação x");
		mov.setDoc(doc);
		mov.setDescription("Movimentação de teste");
		mov.setType("Incoming");	// Incoming = entrada de estoque |  Output = saida de estoque
		mov.setSubType("teste");
		
		mov.setItem(itemRepository.findById(13));
		mov.setQtd(5);	//add 5
		Movement response = null;
		response = movcontroll.postMovement(mov);
		//assertEquals(mov.getItem().getQtd(), 0);
		assertThat(mov.getItem().getQtd() < response.getQtd());
		

		
		
		
	}

}
