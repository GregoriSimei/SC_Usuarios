package com.crm.api.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Person;
import com.crm.api.models.User;
import com.crm.api.models.UserRequest;
import com.crm.api.repositories.UserRepository;
import com.crm.api.repositories.UserRequestRepository;

@Service
@Configurable
public class UserRequestBusiness {
	
	@Autowired
	private UserRequestRepository userRequestRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public UserRequest createRequest(Person person) {
		
		UserRequest response = null;
		
		boolean check = this.checkPerson(person);
		
		if (check) {
			response = new UserRequest();
			response.setStatus("Pending Approval");
			response.setPerson(person);
			response.setCreation(new Date());
		}
		
		return response;
	}
	
	private boolean checkPerson(Person person) {
		boolean checkCPF = this.checkCPF(person.getCpf());
		boolean checkBith = this.checkBirthDate(person.getBirth());
		return checkCPF && checkBith;
	}
	
	private boolean checkCPF(String CPF) {
		boolean check = CPF.isBlank();
		return !check;
	}
	
	private boolean checkBirthDate(Date date) {
		
		boolean check = false;
		
		int tolerance = 200;
		int adjust = 1900;
		
		int toleranceYear = this.getCurrentYear() - tolerance;
		int birthDate = date.getYear() + adjust;
		
		if(birthDate > toleranceYear) {
			check = true;
		}
		
		return check;
	}
	
	private int getCurrentYear() {
		Date now = new Date();
		int year = now.getYear() + 1900;

		return year;
	}

	public UserRequest validateRequest(UserRequest request) {
		Long id = request.getId();
		
		UserRequest response = null;
		
		if(id != null) {
			response = userRequestRepository.findById(id).get();
		}
		
		if(response != null) {
			Date now = new Date();
			response.setModification(now);
			response.setStatus(request.getStatus());
		}
		
		return response;
	}

	public User generateUser(UserRequest request) {
		User newUser = null;
		
		String approval = request.getStatus();
		
		if(approval.equalsIgnoreCase("Approved")){
			Person person = request.getPerson();
			String username = this.generateUsername(person);
			String password = this.generatePassword(person);
			
			String usernameAvaliable = this.checkUsername(username, username, 1);
			
			newUser = new User();
			newUser.setActive(true);
			newUser.setPassword(password);
			newUser.setUsername(usernameAvaliable);
		}
		
		return newUser;
	}
	
	private String generateUsername(Person person) {
		String name = person.getName();
		String begin = name.substring(0,1).toUpperCase();
		int end = this.getCurrentYear();
		
		return begin + end;
	}
	
	private String generatePassword(Person person) {
		String name = person.getName();
		String begin = name.substring(0,1).toUpperCase();
		int end = this.getCurrentYear();
		
		return begin + end;
	}
	
	private String checkUsername(String username, String newUsername, int num) {
		
		User userDB = userRepository.findByUsername(newUsername);
		
		if(userDB != null) {
			newUsername = this.checkUsername(username, username + num, num + 1);
		}
		
		return newUsername;
	}

}
