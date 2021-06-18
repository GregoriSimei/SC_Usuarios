package com.crm.api.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.UserRequest;
import com.crm.api.repositories.UserRequestRepository;

@Service
@Configurable
public class UserRequestService {
	
	@Autowired
	private UserRequestRepository userRequestRepository;
	
	private static String PENDING_APPROVAL = "Pending Approval";
	private static String APPROVED = "Approved";
	private static String NOT_APPROVED = "Not Approved";
	
	public UserRequest save(UserRequest request) {
		request = request.getId() != null?
			this.update(request):
			this.create(request);
		
		return this.userRequestRepository.save(request);
	}
	
	private UserRequest create(UserRequest request) {
		request.setStatus(PENDING_APPROVAL);
		request.setCreation(new Date());
		request = this.persist(request);
		return request;
	}
	
	private UserRequest update(UserRequest request) {
		return request;
	}
	
	public Iterable<UserRequest> getAll() {
		Iterable<UserRequest> response = userRequestRepository.findAll();
		return response;
	}

	public boolean checkFields(UserRequest request) {
		return request.getPerson() != null;
	}
	
	private UserRequest persist(UserRequest request) {
		return this.userRequestRepository.save(request);
	}

	public boolean checkStatus(String status) {
		return status.equalsIgnoreCase(PENDING_APPROVAL) ||
			   status.equalsIgnoreCase(APPROVED) ||
			   status.equalsIgnoreCase(NOT_APPROVED);
	}

	public UserRequest requestApproved(UserRequest request) {
		request.setModification(new Date());
		request.setStatus(APPROVED);
		return this.save(request);
	}

	public UserRequest requestNotApproved(UserRequest request) {
		request.setModification(new Date());
		request.setStatus(NOT_APPROVED);
		return this.save(request);
	}

}
