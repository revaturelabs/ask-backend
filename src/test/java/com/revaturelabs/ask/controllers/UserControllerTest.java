package com.revaturelabs.ask.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;
import com.revaturelabs.ask.question.Question;
import com.revaturelabs.ask.user.User;
import com.revaturelabs.ask.user.UserConflictException;
import com.revaturelabs.ask.user.UserController;
import com.revaturelabs.ask.user.UserNotFoundException;
import com.revaturelabs.ask.user.UserService;

/**
 * @author Bryan Ritter, David Blitz, Efrain Vila
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Test
	public void contextLoads() {
	}

	@MockBean
	UserService userServiceMock;

	@Autowired
	UserController userControllerImpl;

	/*
	 * Testing User Controller
	 */
	@Test
	public void testFindAllUsers() {
		List<User> list = new ArrayList<User>();
		User testUser = new User();
		testUser.setId(10);
		testUser.setUsername("username");
		testUser.setPassword("password");
		list.add(testUser);
		Page<User> page = new PageImpl<>(list);
		when(userServiceMock.findAll(0, 20)).thenReturn(page);
		assertEquals(ResponseEntity.ok(page.getContent()), userControllerImpl.findAll(0, 20));
		assertEquals(ResponseEntity.ok(page.getContent()), userControllerImpl.findAll(null, null));
	}

	@Test
	public void testUserUpdateOrCreate() {
		User testUser = new User();
		testUser.setId(10);
		testUser.setUsername("username");
		testUser.setPassword("password");

		User testUser2 = new User();
		testUser2.setId(11);
		testUser2.setUsername("username");
		testUser2.setPassword("password");

		when(userServiceMock.createOrUpdate(testUser)).thenReturn(testUser);
		assertEquals(testUser, userControllerImpl.createOrUpdate(testUser, 10));
		when(userServiceMock.createOrUpdate(testUser2)).thenReturn(testUser2);
		assertEquals(testUser2, userControllerImpl.createOrUpdate(testUser, 11));
	}

	@Test(expected = ResponseStatusException.class)
	public void testFailedUserUpdateOrCreate() {

		User testUser = new User();
		testUser.setId(10);
		testUser.setUsername("username");
		testUser.setPassword("password");

		when(userServiceMock.createOrUpdate(testUser)).thenThrow(UserConflictException.class);
		userControllerImpl.createOrUpdate(testUser, 0);
	}

	@Test
	public void testUpdateUser() {
		User testUser = new User();
		testUser.setId(10);
		testUser.setUsername("username");
		testUser.setPassword("password");

		when(userServiceMock.update(testUser)).thenReturn(testUser);
		assertEquals(testUser, userControllerImpl.updateUser(testUser, 10));
	}

	@Test(expected = ResponseStatusException.class)
	public void testFailedUpdateUserUserConflictException() {
		User testUser = new User();
		testUser.setId(10);
		testUser.setUsername("username");
		testUser.setPassword("password");

		when(userServiceMock.update(testUser)).thenThrow(UserConflictException.class);
		userControllerImpl.updateUser(testUser, 10);
	}

	@Test(expected = ResponseStatusException.class)
	public void testFailedUpdateUserUserNotFoundException() {
		User testUser = new User();
		testUser.setId(10);
		testUser.setUsername("username");
		testUser.setPassword("password");

		when(userServiceMock.update(testUser)).thenThrow(UserNotFoundException.class);
		userControllerImpl.updateUser(testUser, 10);
	}

	@Test(expected = ResponseStatusException.class)
	public void testDeleteUser() {
		User testUser = new User();
		testUser.setId(10);
		testUser.setUsername("username");
		testUser.setPassword("password");

		when(userServiceMock.createOrUpdate(testUser)).thenReturn(testUser);
		doNothing().when(userServiceMock).delete(10);
		userControllerImpl.deleteUser(10);
		when(userServiceMock.findById(10)).thenThrow(UserNotFoundException.class);
		userControllerImpl.findById(10);
	}

	@Test(expected = ResponseStatusException.class)
	public void testFailedDeleteUser() {
		doThrow(UserNotFoundException.class).when(userServiceMock).delete(10);
		userControllerImpl.deleteUser(10);
	}

	@Test
	public void testUserGetQuestions() {
		Set<Question> questions = new HashSet<Question>();
		Question testQuestion1 = new Question();
		questions.add(testQuestion1);
		User testUser = new User();
		testUser.setId(10);
		testUser.setUsername("username");
		testUser.setPassword("password");
		testUser.setQuestions(questions);

		when(userServiceMock.findById(10)).thenReturn(testUser);
		assertEquals(ResponseEntity.ok(testUser.getQuestions()), userControllerImpl.getQuestions(10));
	}

	@Test(expected = ResponseStatusException.class)
	public void testFailedUserGetQuestions() {
		when(userServiceMock.findById(10)).thenThrow(UserNotFoundException.class);
		userControllerImpl.getQuestions(10);
	}

	@Test
	public void testUpdateUserTags() {
		User testUser = new User();
		testUser.setId(10);
		testUser.setUsername("username");
		testUser.setPassword("password");

		when(userServiceMock.updateTags(testUser)).thenReturn(testUser);
		assertEquals(ResponseEntity.ok(testUser), userControllerImpl.updateUserTags(testUser, 10));
	}

	@Test(expected = ResponseStatusException.class)
	public void testFailedUpdateUserTags() {
		User testUser = new User();
		testUser.setId(10);
		testUser.setUsername("username");
		testUser.setPassword("password");

		when(userServiceMock.updateTags(testUser)).thenThrow(UserNotFoundException.class);
		userControllerImpl.updateUserTags(testUser, 10);
	}

	@Test
	public void testGettingUserById() {
		int index = 4;
		User exampleUser = new User();

		when((userServiceMock.findById(index))).thenReturn(exampleUser);

		assertEquals(ResponseEntity.ok(exampleUser), userControllerImpl.findById(index));
	}

	@Test(expected = ResponseStatusException.class)
	public void testFailedGetUserById() {
		when(userServiceMock.findById(10)).thenThrow(UserNotFoundException.class);
		userControllerImpl.findById(10);
	}

	/**
	 * testUpdatingUser checks against submitting the same data.
	 */
	@Test(expected = UserConflictException.class)
	public void testUpdatingUser() {
		int index = 1; // the last object in the database so far.
		User exampleUser = new User();

		exampleUser.setId(index);
		exampleUser.setUsername("retweet");
		exampleUser.setPassword("weguiawej");
		exampleUser.setExpert(false);
		userServiceMock.create(exampleUser);

		when((userServiceMock.findById(index))).thenReturn(exampleUser);

		exampleUser.setId(index);
		exampleUser.setUsername("retweet");
		exampleUser.setPassword("weguiawej");
		exampleUser.setExpert(false);

		when((userServiceMock.update(exampleUser))).thenThrow(UserConflictException.class);

		// actual update
		exampleUser.setUsername("blahblahblah");
		exampleUser.setPassword("djfjgjofoo");
		exampleUser.setExpert(true);

		when((userServiceMock.update(exampleUser))).thenReturn(exampleUser);

		assertEquals(exampleUser, userControllerImpl.findById(index));
	}

	/**
	 * testAddingUser tests if adding user functions.
	 */
	@Test
	public void testAddingUser() {
		int index = 1;
		User exampleUser = new User();
		exampleUser.setId(index);
		exampleUser.setUsername("blah");
		exampleUser.setPassword("dsafjawjf");

		when((userServiceMock.create(exampleUser))).thenReturn(exampleUser);

		assertEquals(exampleUser, userControllerImpl.createUser(exampleUser));
	}

	/**
	 * testCreatingOrUpdatingUser_Create checks the creation functionality of
	 * createOrUpdate.
	 */
	@Test(expected = UserNotFoundException.class)
	public void testCreatingOrUpdateUser_Create() {
		// updating
		int index = 1; // the last object in the database so far.
		User exampleUser = new User();

		exampleUser.setId(index);
		exampleUser.setUsername("retweet");
		exampleUser.setPassword("weguiawej");
		exampleUser.setExpert(false);

		when((userServiceMock.createOrUpdate(exampleUser))).thenThrow(UserNotFoundException.class);

		when((userServiceMock.createOrUpdate(exampleUser))).thenReturn(exampleUser);
		assertEquals(exampleUser, userControllerImpl.createUser(exampleUser));
	}

	/**
	 * testCreatingOrUpdatingUser_Update checks the updating functionality of
	 * createOrUpdate.
	 * 
	 * It checks against a duplicate new user if the user with the same data already
	 * exists.
	 */
	@Test(expected = UserConflictException.class)
	public void testCreatingOrUpdatingUser_Update() {
		// updating
		int index = 1; // the last object in the database so far.
		User exampleUser = new User();

		exampleUser.setId(index);
		exampleUser.setUsername("retweet");
		exampleUser.setPassword("weguiawej");
		exampleUser.setExpert(false);

		when((userServiceMock.createOrUpdate(exampleUser))).thenReturn(exampleUser);
		assertEquals(exampleUser, userServiceMock.createOrUpdate(exampleUser));

		User anotherExampleUser = new User();

		anotherExampleUser.setId(index);
		anotherExampleUser.setUsername("retweet");
		anotherExampleUser.setPassword("weguiawej");
		anotherExampleUser.setExpert(false);

		when((userServiceMock.createOrUpdate(anotherExampleUser))).thenThrow(UserConflictException.class);
		assertEquals(anotherExampleUser, userServiceMock.createOrUpdate(anotherExampleUser));

		when((userServiceMock.findById(index))).thenReturn(exampleUser);
		// creating
		exampleUser.setId(index);
		exampleUser.setUsername("retweqwgwe");
		exampleUser.setPassword("weguiawgwgwj");
		exampleUser.setExpert(false);

		when((userServiceMock.createOrUpdate(exampleUser))).thenReturn(exampleUser);

		assertEquals(exampleUser, userControllerImpl.createUser(exampleUser));
	}
}
