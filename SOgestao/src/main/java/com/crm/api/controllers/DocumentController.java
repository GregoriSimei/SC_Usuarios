package com.crm.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.api.models.Document;
import com.crm.api.service.DocumentService;

@RestController
@RequestMapping("/document")
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	
	@GetMapping(produces = "application/json")
	public Document getById(@RequestParam("id") Long id) {
		return this.documentService.findById(id);
	}
}
