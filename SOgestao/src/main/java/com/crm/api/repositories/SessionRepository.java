package com.crm.api.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.crm.api.models.Session;

public interface SessionRepository extends CrudRepository<Session, Long>{
	
	List<Session> findByStatus(String[] status);

}
