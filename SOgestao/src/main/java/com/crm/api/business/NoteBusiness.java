package com.crm.api.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.PromissoryNote;
import com.crm.api.service.NoteService;

@Service
@Configurable
public class NoteBusiness {

	@Autowired
	private NoteService noteService;
	
	private static String PAYMENT_PENDING = "Payment Pending";
	
	public PromissoryNote create(PromissoryNote note) {
		boolean checkNote = this.checkNote(note);
		note = checkNote ? 
				this.createManager(note):
				null;
		
		note = note != null ? 
				this.saveNote(note):
				note;
		
		return note;
	}

	public PromissoryNote saveNote(PromissoryNote note) {
		note.setModification(new Date());
		return this.noteService.save(note);
	}

	private PromissoryNote createManager(PromissoryNote note) {
		note.setCreation(new Date());
		note = this.setDueDate(note);
		note = this.setInstallmentsValue(note);
		note.setStatus(PAYMENT_PENDING);
		note.setFees(0.02);
		note.setAssessment(2.3);
		return note;
	}

	private PromissoryNote setInstallmentsValue(PromissoryNote note) {
		double total = note.getTotal();
		int installments = note.getInstallments();
		
		double currentInstallmentValue = total / installments;
		
		note.setCurrent(currentInstallmentValue);
		note.setCurrentInstallment(1);
		
		return note;
	}

	private PromissoryNote setDueDate(PromissoryNote note) {
		Date currentDate = new Date();
		int day = currentDate.getDate();
		int daysPeriod = 3;
		int year = currentDate.getYear();
		int month = currentDate.getMonth();
		
		int limitDay = day + daysPeriod;
		
		if(limitDay > 30) {
			limitDay = 30 - limitDay;
			month++;
		}
		
		Date dueDate = new Date(year, month, limitDay);
		
		note.setDueDate(dueDate);
		
		return note;
	}

	private boolean checkNote(PromissoryNote note) {
		return this.noteService.checkFields(note);
	}
	
}
