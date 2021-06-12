package com.crm.api.service;

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
	
}
