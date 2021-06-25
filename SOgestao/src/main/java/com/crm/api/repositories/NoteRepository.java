package com.crm.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.crm.api.models.PromissoryNote;

public interface NoteRepository extends CrudRepository<PromissoryNote, Long>{

}
