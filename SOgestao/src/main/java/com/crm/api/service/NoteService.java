package com.crm.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.PromissoryNote;
import com.crm.api.repositories.NoteRepository;

@Service
@Configurable
public class NoteService {
	
	@Autowired
	private NoteRepository noteRepository;

	public boolean checkFields(PromissoryNote note) {
		return note.getInstallments() != 0 &&
			   note.getTotal() != 0.0;
	}

	public PromissoryNote save(PromissoryNote note) {
		note = this.noteRepository.save(note);
		return note;
	}

	public PromissoryNote findById(Long id) {
		return this.noteRepository.findById(id).get();
	}

	public PromissoryNote cancel(PromissoryNote note) {
		note.setStatus("Closed");
		note = this.save(note);
		return note;
	}
	
}
