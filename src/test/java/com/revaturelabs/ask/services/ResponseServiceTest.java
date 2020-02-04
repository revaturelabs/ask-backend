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
import org.springframework.test.context.junit4.SpringRunner;
import com.revaturelabs.ask.response.Response;
import com.revaturelabs.ask.response.ResponseConflictException;
import com.revaturelabs.ask.response.ResponseController;
import com.revaturelabs.ask.response.ResponseNotFoundException;
import com.revaturelabs.ask.response.ResponseRepository;
import com.revaturelabs.ask.response.ResponseService;
import com.revaturelabs.ask.response.ResponseServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResponseServiceTest {

	@Test
	public void contextLoads() {
	}

	static ServiceMockData mockData;

	@Mock
	ResponseRepository responseRepositoryMock;

	@Mock
	ResponseService responseServiceMock;

	@InjectMocks
	ResponseService responseServiceImpl = new ResponseServiceImpl();

	@InjectMocks
	ResponseController responseControllerImpl = new ResponseController();

	/**
	 * Test find all responses
	 * 
	 */
	@Test
	public void getAllResponsesTest() {
		when(responseRepositoryMock.findAll()).thenReturn(ServiceMockData.responseReturnList);

		assertEquals(ServiceMockData.responseReturnList, responseServiceImpl.getAll());
	}

	/**
	 * Test find a response by id
	 * 
	 */
	@Test
	public void getResponseByIdTest() {
		when(responseRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testResponse1));

		assertEquals(ServiceMockData.testResponse1, responseServiceImpl.getById(1));
	}

	/**
	 * Test find a valid response by id
	 */
	@Test
	public void getAccurateResponseByIdTest() {
		when(responseRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testResponse1));

		assertNotEquals(ServiceMockData.testResponse2, responseServiceImpl.getById(1));
	}

	/**
	 * Test failure of response retrieval given invalid response id
	 */
	@Test(expected = ResponseNotFoundException.class)
	public void invalidResponseIdFailureTest() {
		when(responseRepositoryMock.findById(1)).thenReturn(Optional.empty());

		responseServiceImpl.getById(1);
	}

	/**
	 * Test response creation
	 * 
	 */
	@Test
	public void responseCreationAccuracyTest() {
		when(responseRepositoryMock.save(ServiceMockData.testResponse1)).thenReturn(ServiceMockData.testResponse1PostCreate);

		assertEquals(ServiceMockData.testResponse1PostCreate, responseServiceImpl.create(ServiceMockData.testResponse1));
	}

	/**
	 * Test updating a response
	 */
	@Test
	public void responseUpdateTest() {
		Response testResponse1UpdateInfo = new Response();
		testResponse1UpdateInfo.setId(1);
		testResponse1UpdateInfo.setBody("Test body update");

		when(responseRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testResponse1));
		when(responseRepositoryMock.save(testResponse1UpdateInfo)).thenReturn(ServiceMockData.testResponse1);

		assertEquals(ServiceMockData.testResponse1, responseServiceImpl.update(testResponse1UpdateInfo));
	}

	/**
	 * Test updating failure for non-existent response
	 * 
	 */
	@Test(expected = ResponseNotFoundException.class)
	public void responseUpdateNonExistantFailureTest() {
		Response nonExistentResponse = new Response();
		nonExistentResponse.setId(5);

		when(responseRepositoryMock.findById(5)).thenReturn(Optional.empty());

		responseServiceImpl.update(nonExistentResponse);
	}

	/**
	 * Testing data integrity violation failure for updating a response
	 * 
	 */
	@Test(expected = ResponseConflictException.class)
	public void responseFailureToUpdateTest() {
		when(responseRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testResponse1));
		when(responseRepositoryMock.save(ServiceMockData.testResponse1)).thenThrow(DataIntegrityViolationException.class);

		responseServiceImpl.update(ServiceMockData.testResponse1);
	}

	/**
	 * Test create or update response
	 * 
	 */
	@Test
	public void createOrUpdateResponseTest() {
		Response nonExistentResponse = new Response();
		nonExistentResponse.setId(4);
		nonExistentResponse.setBody("New response");

		when(responseRepositoryMock.save(nonExistentResponse)).thenReturn(nonExistentResponse);

		responseServiceImpl.createOrUpdate(nonExistentResponse);
		assertEquals(nonExistentResponse.getId(), 4);
		assertEquals(nonExistentResponse.getBody(), "New response");
	}

	/**
	 * Testing data integrity violation failure for creating or updating a response
	 * 
	 */
	@Test(expected = ResponseConflictException.class)
	public void responseFailureToCreateOrUpdateTest() {
		when(responseRepositoryMock.save(ServiceMockData.testResponse1)).thenThrow(DataIntegrityViolationException.class);

		responseServiceImpl.createOrUpdate(ServiceMockData.testResponse1);
	}

	/**
	 * Test deletion of a response
	 * 
	 */
	@Test
	public void responseDeletionTest() {
		when(responseRepositoryMock.existsById(1)).thenReturn(true);
		Mockito.doNothing().when(responseRepositoryMock).deleteById(1);

		responseServiceImpl.delete(1);
		when(responseRepositoryMock.existsById(1)).thenReturn(false);
		assertFalse(responseRepositoryMock.existsById(1));
	}

	/**
	 * Test exception throw of invalid response when deleting
	 */
	@Test(expected = ResponseNotFoundException.class)
	public void responseDeleteNonExistantResponseTest() {
		when(responseRepositoryMock.existsById(10)).thenReturn(false);
		Mockito.doNothing().when(responseRepositoryMock).deleteById(10);

		responseServiceImpl.delete(10);
	}
}
