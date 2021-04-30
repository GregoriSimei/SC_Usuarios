package com.crm.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.User;
import com.crm.api.repositories.UserRepository;

@Service
@Configurable
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public boolean validateUser(User user) {
		Long id = user.getId();
		String username = user.getUsername();
		String password = user.getPassword();
		
		boolean response = false;
		
		if(id != null) {
			User userDB = userRepository.findById(id).get();
			String userDBPassword = userDB.getPassword();
			String userDBUsername = userDB.getUsername();
			
			if(username == userDBPassword && password == userDBUsername) {
				response = true;
			}
		}
		
		return response;
	}
}
