package com.crm.api.movement;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.crm.api.controllers.UserRequestController;
import com.crm.api.models.UserRequest;
import com.crm.api.repositories.UserRepository;
import com.crm.api.repositories.UserRequestRepository;

@SpringBootTest
public class RequestTest {
	@Autowired
	private UserRequestController ureqcontroll;
	@Autowired
	private UserRequestRepository reqRepository;
	@Autowired
	private UserRepository userRepository;
	
	
	@Test
	void aproveRequest() {
		UserRequest req = null;
		req = reqRepository.findById(7);
		req.setStatus("Approved");
		ureqcontroll.approvalRequest(req);
		assertEquals(req.getPerson().getUser(), userRepository.findByUsername(req.getPerson().getUser().getUsername()));
	}

}
