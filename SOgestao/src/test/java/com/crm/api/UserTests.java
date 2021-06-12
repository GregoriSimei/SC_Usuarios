package com.crm.api;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crm.api.models.User;
import com.crm.api.repositories.UserRepository;
import com.crm.api.service.UserService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class UserTests {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	private List<User> users = new ArrayList<User>();
	
	@BeforeAll
	protected void criarUsuario() {
		User userToCreate = new User();
		userToCreate.setUsername("user");
		userToCreate.setPassword("1234");
		userToCreate.setEmail("gregori@teste.com");
		userToCreate.setActive(true);
		userToCreate.setLevel("Admin");
		users.add(this.userRepository
					.save(userToCreate));
	}
	
	@Test
	public void isPosibleToCreateAnUserWithUsernameAndPasword() {
		User userToCreate = new User();
		userToCreate.setUsername("user2");
		userToCreate.setPassword("1234");
		userToCreate.setEmail("gregori1@teste.com");
		userToCreate.setLevel("Admin");
		
		userToCreate = this.userService.save(userToCreate);
		
		assertNotEquals(null, userToCreate);
		assertEquals(true, userToCreate.getActive());
	}
	
	@Test
	public void notIsPosibleToCreateAnUserWithotUsername() {
		User userToCreate = new User();
		userToCreate.setPassword("1234");
		userToCreate.setEmail("gregori2@teste.com");
		userToCreate.setActive(true);
		userToCreate.setLevel("Admin");
		
		userToCreate = this.userService.save(userToCreate);
		
		assertEquals(null, userToCreate);
	}
	
	@Test
	public void notIsPosibleToCreateAnUserWithotPasword() {
		User userToCreate = new User();
		userToCreate.setUsername("user3");
		userToCreate.setEmail("gregori3@teste.com");
		userToCreate.setActive(true);
		userToCreate.setLevel("Admin");
		
		userToCreate = this.userService.save(userToCreate);
		
		assertEquals(null, userToCreate);
	}
	
	@Test
	public void whenTryToCreateAnUserWithFalseStatusFieldItIsChangedToTrue() {
		User userToCreate = new User();
		userToCreate.setUsername("user5");
		userToCreate.setPassword("1234");
		userToCreate.setEmail("gregori4@teste.com");
		userToCreate.setActive(false);
		userToCreate.setLevel("Admin");
		
		userToCreate = this.userService.save(userToCreate);
		
		assertEquals(true, userToCreate.getActive());
	}
	
	@Test
	public void validationReturnTrueCaseCorrectUsernameAndPassword() {
		User user = users.get(0);
		boolean validacao = this.userService.validateUser(user);
		assertEquals(validacao, true);
	}
	
	@Test
	public void validationReturnFalseCaseWrongUsernameAndCorrectPassword() {
		User user = users.get(0);
		
		User userTest = new User();
		userTest.setId(user.getId());
		userTest.setUsername("WRONG USERNAME");
		userTest.setPassword(user.getPassword());
		
		boolean validacao = this.userService.validateUser(userTest);
		
		assertEquals(validacao, false);
	}
	
	@Test
	public void validationReturnFalseCaseCorectUsernameAndWrongPassword() {
		User user = users.get(0);
		
		User userTest = new User();
		userTest.setId(user.getId());
		userTest.setUsername(user.getUsername());
		userTest.setPassword("WRONG PASSWORD");
		
		boolean validacao = this.userService.validateUser(userTest);
		
		assertEquals(validacao, false);
	}
	
	@Test
	public void validationReturnFalseCaseUserWithoutId() {
		User user = users.get(0);
		
		User userTest = new User();
		userTest.setUsername(user.getUsername());
		userTest.setPassword(user.getPassword());
		
		boolean validacao = this.userService.validateUser(userTest);

		assertEquals(validacao, false);
	}
	
	@Test
	public void activeFielsTurnToFalseWhenDeleteAnUser() {
		User user = users.get(0);
		boolean deleted = this.userService.delete(user);
		
		assertEquals(true, deleted);
	}
	
	@AfterAll
	public void removerDadosDoBanco() {
		for(User user: users) {
			this.userRepository.delete(user);
		}
	}
	

}
