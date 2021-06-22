package com.crm.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Document;
import com.crm.api.models.Movement;
import com.crm.api.repositories.MovementRepository;

@Service
@Configurable
public class MovementService {

	@Autowired
	private MovementRepository movementRepository;
	
	public Movement save(Movement movement) {
		return this.movementRepository.save(movement);
	}
	
	public Movement findById(Long id) {
		return this.movementRepository.findById(id).get();
	}
	
	public List<Movement> getAll(Long id) {
		List<Movement> movements = movementRepository.findByDepositId(id);
		return movements;
	}	

	public boolean checkFields(Movement movement) {
		return movement.getDeposit() != null &&
			   movement.getDescription() != null &&
			   movement.getDoc() != null &&
			   movement.getItem() != null &&
			   movement.getQtd() != 0 &&
			   movement.getSubType() != null &&
			   movement.getUser() != null;
	}
}
