package com.crm.api.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Branch;
import com.crm.api.models.Deposit;
import com.crm.api.models.Item;
import com.crm.api.service.BranchService;
import com.crm.api.service.DepositService;
import com.crm.api.service.ItemService;

@Service
@Configurable
public class DepositBusiness {
	
	@Autowired
	private DepositService depositService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private BranchService branchService;

	public Deposit createDeposit(Deposit deposit, Long branchId) {
		boolean checkFields = this.checkFields(deposit);
		deposit.setItems(null);
		
		return checkFields ?
				this.saveDeposit(deposit, branchId):
				null;
	}

	private Deposit saveDeposit(Deposit deposit, Long branchId) {
		Branch branch = this.branchService.findById(branchId);
		deposit = this.depositService.save(deposit);
		branch.setDeposit(deposit);
		this.depositService.save(deposit);
		
		return deposit;
	}

	public boolean checkFields(Deposit deposit) {
		boolean checkDeposit = this.checkDepositFields(deposit);
		boolean checkItems = deposit.getItems() != null ?
				this.checkItemsFields(deposit):
				true;
		
		return checkDeposit && checkItems;
	}
	
	private boolean checkDepositFields(Deposit deposit) {
		return this.depositService.checkFields(deposit);
	}
	
	private boolean checkItemsFields(Deposit deposit) {
		for(Item item : deposit.getItems()) {
			if(!this.itemService.checkFields(item)) {
				return false;
			}
		}
		return true;
	}
	
}
