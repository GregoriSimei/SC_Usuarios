package com.crm.api.service;

import java.util.Date;
import java.util.List;

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
	
	private final String ACTIVE = "Active";
	private final String FINISHED = "Finished";
	private final String CLOSED = "Closed";
	
	public Session generate() {
		Session session = new Session();
		session.setDate(new Date());
		session.setStatus(ACTIVE);
		session = this.sessionRepository.save(session);
		return session;
	}
	
	public Session getById(long id) {
		Session session = this.sessionRepository.findById(id).get();
		return session;
	}

	public Session finish(Session session) {
		session.setStatus(FINISHED);
		session = this.sessionRepository.save(session);
		return session;
	}

	public Session cancel(Session session) {
		session.setStatus(CLOSED);
		session = this.sessionRepository.save(session);
		return session;
	}

	public List<Session> getActiveSession(){
		String[] status = new String[] {ACTIVE,FINISHED};
		return this.sessionRepository.findByStatus(status);
	}
}
