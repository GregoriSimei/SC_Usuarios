package com.crm.api.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Session;

@Service
@Configurable
public class SessionService {

	public Session generateSession() {
		Session session = new Session();
		session.setDate(new Date());
		session.setStatus("active");
		
		return session;
	}

}
