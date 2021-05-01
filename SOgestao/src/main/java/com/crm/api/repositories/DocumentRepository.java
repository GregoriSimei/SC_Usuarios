package com.crm.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.crm.api.models.Document;

public interface DocumentRepository extends CrudRepository<Document, Long>{

}
