package com.crm.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Deposit;
import com.crm.api.repositories.DepositRepository;

@Service
@Configurable
public class DepositService {
	
	@Autowired
	private DepositRepository depositRepository;
	
	public Deposit save(Deposit deposit) {
		return this.depositRepository.save(deposit);
	}
	
	public Deposit saveOnlyName(Deposit deposit) {
		deposit.setItems(
				this.depositRepository
				.findById(deposit.getId())
				.get()
				.getItems()
			);
		return this.save(deposit);
	}
	
	public Deposit getById(long id) {
		Deposit deposit = depositRepository.findById(id);
		return deposit;
	}
	
	public Deposit findById(Long id) {
		return this.depositRepository.findById(id).get();
	}

	public boolean checkFields(Deposit deposit) {
		return deposit.getName() != null;
	}
}
