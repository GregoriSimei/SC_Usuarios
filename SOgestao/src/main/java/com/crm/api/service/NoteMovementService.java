package com.crm.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.NoteMovement;
import com.crm.api.repositories.NoteMovementRepository;

@Service
@Configurable
public class NoteMovementService {
	
	@Autowired
	private NoteMovementRepository movementRepository;

	public NoteMovement save(NoteMovement movement) {
		return this.movementRepository.save(movement);
	}
	
	public boolean checkFields(NoteMovement movement) {
		return movement.getType() != null &&
			   movement.getValue() != 0.0;
	}

}
