package com.revaturelabs.ask.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import com.revaturelabs.ask.user.User;
import com.revaturelabs.ask.user.UserConflictException;
import com.revaturelabs.ask.user.UserController;
import com.revaturelabs.ask.user.UserNotFoundException;
import com.revaturelabs.ask.user.UserRepository;
import com.revaturelabs.ask.user.UserService;
import com.revaturelabs.ask.user.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Test
	public void contextLoads() {
	}

	static ServiceMockData mockData;

	@Mock
	UserRepository userRepositoryMock;

	@Mock
	UserService userServiceMock;

	@InjectMocks
	UserService userServiceImpl = new UserServiceImpl();

	@InjectMocks
	UserController userControllerImpl = new UserController();

	/**
	 * Test of findAll users
	 * 
	 */
	@Test
	public void usersFindAllTest() {
		when(userRepositoryMock.findAll(PageRequest.of(0, 5))).thenReturn(ServiceMockData.returnUsersPage);
		assertEquals(ServiceMockData.returnUsersPage, userServiceImpl.findAll(0, 5));
	}

	/**
	 * Test of findById
	 */
	@Test
	public void usersFindByIdTest() {
		when(userRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testUser1));

		assertEquals(ServiceMockData.testUser1, userServiceImpl.findById(1));
	}

	/**
	 * Test of accurate findById
	 */
	@Test
	public void usersFindAccuratelyByIdTest() {
		when(userRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testUser1));

		assertNotEquals(ServiceMockData.testUser2, userServiceImpl.findById(1));
	}

	/**
	 * Test of thrown exception when given an invalid ID
	 */
	@Test(expected = UserNotFoundException.class)
	public void usersNotFoundIdExceptionTest() {
		when(userRepositoryMock.findById(1)).thenReturn(Optional.empty());

		userServiceImpl.findById(1);
	}

	/**
	 * Test user creation
	 * 
	 */
	@Test
	public void userCreationAccuracyTest() {
		when(userRepositoryMock.save(ServiceMockData.testUser1)).thenReturn(ServiceMockData.testUser1PostCreate);

		assertEquals(ServiceMockData.testUser1PostCreate, userServiceImpl.create(ServiceMockData.testUser1));
	}

	/**
	 * Test updating a user
	 */
	@Test
	public void userUpdateTest() {
		User testUser1UpdateInfo = new User();
		testUser1UpdateInfo.setId(1);
		testUser1UpdateInfo.setUsername("testUsername");

		when(userRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testUser1));
		when(userRepositoryMock.save(testUser1UpdateInfo)).thenReturn(ServiceMockData.testUser1);

		assertEquals(ServiceMockData.testUser1, userServiceImpl.update(testUser1UpdateInfo));
	}

	/**
	 * Test updating failure for non-existent user
	 * 
	 */
	@Test(expected = UserNotFoundException.class)
	public void userUpdateNonExistantFailureTest() {
		User nonExistentUser = new User();
		nonExistentUser.setId(5);

		when(userRepositoryMock.findById(5)).thenReturn(Optional.empty());

		userServiceImpl.update(nonExistentUser);
	}

	/**
	 * Testing data integrity violation failure for updating a user
	 * 
	 */
	@Test(expected = UserConflictException.class)
	public void userFailureToUpdateTest() {
		when(userRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testUser1));
		when(userRepositoryMock.save(ServiceMockData.testUser1)).thenThrow(DataIntegrityViolationException.class);

		userServiceImpl.update(ServiceMockData.testUser1);
	}

	/**
	 * Test create or update user
	 * 
	 */
	@Test
	public void createOrUpdateUserTest() {
		User nonExistentUser = new User();
		nonExistentUser.setId(4);

		when(userRepositoryMock.save(nonExistentUser)).thenReturn(nonExistentUser);

		userServiceImpl.createOrUpdate(nonExistentUser);
		assertEquals(nonExistentUser.getId(), 4);
	}

	/**
	 * Testing data integrity violation failure for creating or updating a user
	 * 
	 */
	@Test(expected = UserConflictException.class)
	public void userFailureToCreateOrUpdateTest() {
		when(userRepositoryMock.save(ServiceMockData.testUser1)).thenThrow(DataIntegrityViolationException.class);

		userServiceImpl.createOrUpdate(ServiceMockData.testUser1);
	}

	/**
	 * Test deletion of a user
	 * 
	 */
	@Test
	public void userDeletionTest() {
		when(userRepositoryMock.existsById(1)).thenReturn(true);
		Mockito.doNothing().when(userRepositoryMock).deleteById(1);

		userServiceImpl.delete(1);
		when(userRepositoryMock.existsById(1)).thenReturn(false);
		assertFalse(userRepositoryMock.existsById(1));
	}

	/**
	 * Test exception throw of invalid response when deleting
	 */
	@Test(expected = UserNotFoundException.class)
	public void userDeleteNonExistantResponseTest() {
		when(userRepositoryMock.existsById(10)).thenReturn(false);
		Mockito.doNothing().when(userRepositoryMock).deleteById(10);

		userServiceImpl.delete(10);
	}

	/**
	 * Test updating user tags
	 */
	@Test
	public void userUpdateTagsTest() {
		when(userRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testUser2));
		when(userRepositoryMock.save(ServiceMockData.testUser2)).thenReturn(ServiceMockData.testUser2);

		userServiceImpl.updateTags(ServiceMockData.testUser1);

		assertEquals(ServiceMockData.testUser2.getExpertTags(), ServiceMockData.testUser1.getExpertTags());
	}

	/**
	 * Test for proper failure when updating user tags that don't exist
	 */
	@Test(expected = UserNotFoundException.class)
	public void userUpdateTagsFailureTest() {
		when(userRepositoryMock.findById(1)).thenReturn(Optional.empty());
		when(userRepositoryMock.save(ServiceMockData.testUser1)).thenReturn(ServiceMockData.testUser1);

		userServiceImpl.updateTags(ServiceMockData.testUser1);
	}
}