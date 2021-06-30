package com.crm.api.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Item;
import com.crm.api.models.NoteMovement;
import com.crm.api.models.PromissoryNote;
import com.crm.api.models.Sale;
import com.crm.api.service.NoteMovementService;
import com.crm.api.service.NoteService;
import com.crm.api.service.SaleService;

@Service
@Configurable
public class PaymentBusiness {
	
	@Autowired
	private NoteMovementService movementService;
	
	@Autowired
	private NoteService noteService;
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private SaleBusiness saleBusiness;
	
	private static String CREATION = "creation";
	private static String PAID = "Paid";
	private static String CANCEL = "Cancel";

	public NoteMovement createMovement(NoteMovement movement, Long noteId) {
		boolean checkFields = this.checkFields(movement);
		boolean checkNoteExist = this.checkNoteExist(noteId);
		boolean checkType = checkFields ? 
				this.checkType(movement):
				false;
		
		movement = checkType && checkNoteExist? 
				this.movementManager(movement, noteId) :
				null;
		
		movement = movement != null?
				this.movementService.save(movement):
				null;
		
		return movement;
	}

	private boolean checkNoteExist(Long noteId) {
		PromissoryNote note = this.getNote(noteId);
		return note != null;
	}

	private PromissoryNote getNote(Long noteId) {
		return this.noteService.findById(noteId);
	}
	
	private NoteMovement movementManager(NoteMovement movement, Long id) {
		String type = movement.getType();
		
		if(type.contentEquals(CREATION)) {
			movement = this.movementCreation(movement, id);
		}
		else if(type.contentEquals(PAID)) {
			movement = this.movementPaid(movement, id);
		}
		else if(type.contentEquals(CANCEL)) {
			movement = movementCancel(movement, id);
		}
		else {
			movement = null;
		}
		
		return movement;
	}
	
	private NoteMovement movementCancel(NoteMovement movement, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	private NoteMovement movementPaid(NoteMovement movement, Long id) {
		//
		return null;
	}

	private NoteMovement movementCreation(NoteMovement movement, Long noteId) {
		PromissoryNote note = this.getNote(noteId);
		movement.setDate(new Date());
		boolean checkDateToOpen = this.checkDateToOpen(note, movement);
		boolean checkValue = this.checkValuePaid(note, movement);
		
		note = this.notePaid(note);
		
		note = checkDateToOpen && checkValue ?
				this.openNote(note, noteId):
				null;
		
		return movement;
	}

	private PromissoryNote notePaid(PromissoryNote note) {
		int currentInstallment = note.getCurrentInstallment();
		int installments = note.getInstallments();
		
		if(currentInstallment == installments) {
			note.setStatus("Closed");
		}
		else if(currentInstallment > installments) {
			note.setCurrentInstallment(currentInstallment++);
		}
		else {
			note = null;
		}
		
		return note;
	}

	private boolean checkValuePaid(PromissoryNote note, NoteMovement movement) {
		return note.getCurrent() == movement.getValue();
	}

	private PromissoryNote openNote(PromissoryNote note, Long noteId) {
		note.setStatus("Open");
		note = this.noteService.save(note);
		Sale sale = this.saleService.findByNoteId(noteId);
		sale.setStatus("Paid Out");
		this.saleBusiness.save(sale);
		return note;
	}

	private boolean checkDateToOpen(PromissoryNote note, NoteMovement movement) { 
		Date movementDate = movement.getDate();
		Date noteDate = note.getDueDate();
		return noteDate.compareTo(movementDate) >= 0;
	}

	private boolean checkType(NoteMovement movement) {
		String status = movement.getType();
		return status.contentEquals(CREATION) ||
			   status.contentEquals(PAID) ||
			   status.contentEquals(CANCEL);
	}

	private boolean checkFields(NoteMovement movement) {
		return this.movementService.checkFields(movement);
	}
	
}
