package com.crm.api.movement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crm.api.controllers.BranchController;
import com.crm.api.controllers.ItemController;
import com.crm.api.models.Item;
import com.crm.api.repositories.DepositRepository;
import com.crm.api.repositories.ItemRepository;

@SpringBootTest
public class ItemTest {
	@Autowired
	private ItemController itemcontroll;
	@Autowired
	private DepositRepository depRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Test
	void saveItem() {
		Item item = new Item();
		item.setName("Coxinha");
		item.setInId("315");
		item.setOutId("789000001234");
		item.setType("Comestivel");
		item.setDescription("Coxinha de frango com catupiry");
		item.setAvgPrice(3.99);
		Date d = new Date();
		item.setValidity(d);
		item.setQtd(0);
		itemcontroll.postItemInDeposit(item, depRepository.findById(11).getId());
		assertEquals(item, itemRepository.findById(item.getId()));
	}

}
