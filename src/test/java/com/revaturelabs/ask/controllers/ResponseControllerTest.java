package com.revaturelabs.ask.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;
import com.revaturelabs.ask.response.Response;
import com.revaturelabs.ask.response.ResponseConflictException;
import com.revaturelabs.ask.response.ResponseController;
import com.revaturelabs.ask.response.ResponseNotFoundException;
import com.revaturelabs.ask.response.ResponseService;

/**
 * @author Bryan Ritter, David Blitz, Efrain Vila
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResponseControllerTest {

	@Test
	public void contextLoads() {
	}

	@MockBean
	ResponseService responseServiceMock;

	@Autowired
	ResponseController responseControllerImpl;

	/**
	 * Tests related to Responses
	 */

	/**
	 * This will test getting response by Id
	 */
	@Test
	public void testGetResponseById() {
		Response exampleResponse = new Response();
		org.mockito.Mockito.when((this.responseServiceMock.getById(-999))).thenReturn(exampleResponse);
		assertEquals(ResponseEntity.ok(exampleResponse), this.responseControllerImpl.getResponseById(-999));
	}

	@Test(expected = ResponseStatusException.class)
	public void testFailedGetResponseById() {
		when(responseServiceMock.getById(10)).thenThrow(ResponseNotFoundException.class);
		responseControllerImpl.getResponseById(10);
	}

	/**
	 * Tests if creating a response returns the same response
	 */
	@Test
	public void testCreateResponse() {
		Response exampleResponse = new Response();
		org.mockito.Mockito.when(this.responseServiceMock.create(exampleResponse)).thenReturn(exampleResponse);
		assertEquals(exampleResponse, this.responseControllerImpl.createResponse(exampleResponse));
	}

	/**
	 * Tests updating a response if it exists
	 */

	@Test
	public void testUpdateExistingResponse() {
		Response exampleResponse1 = new Response();
		exampleResponse1.setBody("I'm here 1");
		exampleResponse1.setId(-11);
		Response exampleResponse2 = new Response();
		exampleResponse2.setBody("I'm here 2");
		exampleResponse2.setId(-22);

		org.mockito.Mockito.when(this.responseServiceMock.update(exampleResponse2)).thenReturn(exampleResponse2);
		assertEquals(exampleResponse2, this.responseControllerImpl.updateResponse(exampleResponse2, -11));
	}

	/**
	 * Makes sure attempts on a non-existent id results in an error
	 * 
	 * @throws ResponseNotFoundException
	 */
	@Test
	public void testUpdateNonexistingResponse() throws ResponseNotFoundException {
		Response exampleResponse3 = new Response();
		exampleResponse3.setBody("I'm here 3");
		exampleResponse3.setId(-33);

		org.mockito.Mockito.when(this.responseServiceMock.getById(-33)).thenThrow(new ResponseNotFoundException(null));
		assertEquals(null, this.responseControllerImpl.updateResponse(exampleResponse3, -33));
	}

	@Test(expected = ResponseStatusException.class)
	public void testFailedUpdateResponseResponseConflictException() {
		Response testResponse = new Response();
		when(responseServiceMock.update(testResponse)).thenThrow(ResponseConflictException.class);
		responseControllerImpl.updateResponse(testResponse, 1);
	}

	@Test(expected = ResponseStatusException.class)
	public void testFailedUpdateResponseResponseNotFoundException() {
		Response testResponse = new Response();
		when(responseServiceMock.update(testResponse)).thenThrow(ResponseNotFoundException.class);
		responseControllerImpl.updateResponse(testResponse, 1);
	}

	/**
	 * Tests if CreateOrUpdate results in a response being created or updated
	 */
	@Test
	public void TestCreateOrUpdateResponse() {
		Response exampleResponse4 = new Response();
		exampleResponse4.setBody("I'm here 4");
		exampleResponse4.setId(-44);

		org.mockito.Mockito.when(this.responseServiceMock.createOrUpdate(exampleResponse4))
				.thenReturn(exampleResponse4);
		assertEquals(exampleResponse4, this.responseControllerImpl.createOrUpdate(exampleResponse4, -44));
	}

	@Test(expected = ResponseStatusException.class)
	public void testFailedCreateOrUpdateResponse() {
		Response testResponse = new Response();
		when(responseServiceMock.createOrUpdate(testResponse)).thenThrow(ResponseConflictException.class);
		responseControllerImpl.createOrUpdate(testResponse, 10);
	}

	@Test
	public void testGetAllResponses() {
		Response exampleResponse5 = new Response();
		exampleResponse5.setBody("I'm here 5");
		exampleResponse5.setId(-55);
		Response exampleResponse6 = new Response();
		exampleResponse6.setBody("I'm here 6");
		exampleResponse6.setId(-66);
		List<Response> listResponses = new ArrayList<>();
		listResponses.add(exampleResponse5);
		listResponses.add(exampleResponse6);
		org.mockito.Mockito.when(this.responseServiceMock.getAll()).thenReturn(listResponses);
		assertEquals(ResponseEntity.ok(listResponses), this.responseControllerImpl.getAllResponses());
	}

	@Test
	public void testDeleteResponse() {
		Response exampleResponse1 = new Response();
		exampleResponse1.setBody("I'm here 1");
		exampleResponse1.setId(10);
		when(this.responseServiceMock.create(exampleResponse1)).thenReturn(exampleResponse1);
		this.responseControllerImpl.createResponse(exampleResponse1);

		when((this.responseServiceMock.getById(10))).thenReturn(exampleResponse1);
		doNothing().when(responseServiceMock).delete(10);
		responseControllerImpl.deleteResponse(10);

		when((this.responseServiceMock.getById(10))).thenReturn(null);
		Assert.assertNull(this.responseServiceMock.getById(10));
	}

	@Test(expected = ResponseStatusException.class)
	public void testFailedDeleteResponse() {
		doThrow(ResponseNotFoundException.class).when(responseServiceMock).delete(10);
		responseControllerImpl.deleteResponse(10);
	}
}
