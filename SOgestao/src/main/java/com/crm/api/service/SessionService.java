package com.crm.api.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Session;
import com.crm.api.repositories.SessionRepository;

@Service
@Configurable
public class SessionService {
	
	@Autowired
	private SessionRepository sessionRepository;

	public Session generateSession() {
		Session session = new Session();
		session.setDate(new Date());
		session.setStatus("active");
		session = this.sessionRepository.save(session);
		
		return session;
	}
	
	public Session getById(long id) {
		Session session = this.sessionRepository.findById(id).get();
		return session;
	}

}
