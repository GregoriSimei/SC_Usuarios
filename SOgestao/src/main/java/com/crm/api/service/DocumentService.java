package com.crm.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Document;
import com.crm.api.repositories.DocumentRepository;

@Service
@Configurable
public class DocumentService {

	@Autowired
	private DocumentRepository documentRepository;
	
	public Document save(Document document) {
		document = this.documentRepository.save(document);
		return document;
	}
	
	public Document findById(Long id) {
		return this.documentRepository.findById(id).get();
	}

}
