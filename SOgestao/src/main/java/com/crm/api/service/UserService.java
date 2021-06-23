package com.crm.api.service;

import java.util.Date;
import java.util.List;

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
	
	public List<User> findAll(){
		return this.userRepository
					.findByActive(true);
	}
	
	public User save(User user) {
		boolean validateFields = this
					.validateFields(user);
		
		user = validateFields ? 
				this.saveOrUpdate(user):
				null;
		
		return user;
	}
	
	private User saveOrUpdate(User user) {
		User userDB = this.findUserById(user);
		
		user = userDB != null ?
				this.update(user):
				this.create(user);
		
		return this.userRepository.save(user);
	}
	
	private User create(User user) {
		user.setActive(true);
		return user;
	}
	
	private User update(User user) {
		return user;
	}

	public boolean delete(User user) {
		boolean exist = this.validateUser(user);
		
		user = exist ? 
				this.desableUser(user):
				null;
		
		boolean active = user != null ? 
				user.getActive():
				true;
		
		boolean deleted = !active;
		
		return deleted;
	}
	
	private User desableUser(User user) {
		user.setActive(false);
		user = this.userRepository.save(user);
		return user;
	}
	
	public boolean validateUser(User user) {
		User userDB = this.findUserById(user);
		
		boolean validation = userDB != null? 
				this.compareUsers(
						user, 
						userDB
				):
				false;
		
		return validation;
	}
	
	public User findUserById(User user) { 
		Long id = user.getId();
		return id != null ? 
					userRepository
						.findById(id)
						.get() :
					null;
	}
	
	public User findUserById(Long id) { 
		return userRepository
						.findById(id)
						.get();
	}
	
	private boolean compareUsers(User user1, User user2) {
		String username1 = user1.getUsername(); 
		String password1 = user1.getPassword();
		String username2 =  user2.getUsername();
		String password2 = user2.getPassword();
		
		return username1.contentEquals(username2) &&
				password1.contentEquals(password2);
	}
	
	private boolean validateFields(User user) {
		return user.getPassword() != null &&
			   user.getUsername() != null;
	}

	public User createUser(String name, Date birth) {
		System.out.println("Se parou aqui, falhou em gerar username");
		String username = this.generateUsername(name);
		System.out.println("Se parou aqui, falhou em gerar email");
		String email = this.generateEmail(name);
		System.out.println("Se parou aqui, falhou em gerar senha");
		String password = this.generatePassword(name, birth);
		
		User user = new User();
		user.setActive(true);
		user.setEmail(email);
		user.setLevel("New");
		user.setPassword(password);
		user.setUsername(username);
		
		return user;
	}
	
	private String generatePassword(String name, Date birth) {
		return name.substring(0,1)
				+ birth.getDay()
				+ birth.getMonth()
				+ birth.getYear();
	}

	private String generateEmail(String name) {
		String[] names = name.split(" ");
		int qtdNames = names.length;
		String email = names[0] + "." + names[qtdNames -1] + "@company.com.br";
		return email;
	}

	private String generateUsername(String name) {
		Date date = new Date();
		String username = ((String) name.subSequence(0, 1)) 
				+ date.getYear();
		
		username = this.createUsername(username, 0);
		
		return username.toLowerCase();
	}
	
	private String createUsername(String name, int num) {
		String username = num > 0? 
				name+num:
				name;
		System.out.println(username);
		User user = this.userRepository
				.findByUsername(username);
		
		if(user != null) {
			username = this.createUsername(name, num+1);
			System.out.println(username);
		}
		
		System.out.println(username);
		
		return username;
	}
	
}
