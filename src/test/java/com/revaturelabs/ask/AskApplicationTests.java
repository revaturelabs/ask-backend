package com.revaturelabs.ask;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.revaturelabs.ask.response.Response;
import com.revaturelabs.ask.response.ResponseController;
import com.revaturelabs.ask.response.ResponseNotFoundException;
import com.revaturelabs.ask.response.ResponseService;

/**
 * @author Bryan Ritter
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AskApplicationTests {

  @Test
  public void contextLoads() {}


  @MockBean
  ResponseService responseServiceMock;

  @Autowired
  ResponseController responseControllerImpl;

  @Test
  public void testGetResponseById() {

    Response exampleResponse = new Response();
    // exampleResponse.setId(005);
    // exampleResponse.setBody("Hello Testing World");
    // System.out.println(exampleResponse);
    // System.out.println(this.responseControllerImpl.getResponseById(-999));
    org.mockito.Mockito.when((this.responseServiceMock.getById(-999))).thenReturn(exampleResponse);
    // exampleResponse.setId(-3);
    // exampleResponse.setBody("Hey, I'm new");
    // exampleResponse.setQuestionId(-202);
    // System.out.println(exampleResponse);
    // System.out.println(this.responseControllerImpl.getResponseById(-999));
    // These two point to the same object, Is this what we want to test?
    assertEquals(exampleResponse, this.responseControllerImpl.getResponseById(-999));
  }

  /**
   * Tests if creating a response returns the same response
   */
  @Test
  public void testCreateResponse() {
    Response exampleResponse = new Response();
    org.mockito.Mockito.when(this.responseServiceMock.create(exampleResponse))
        .thenReturn(exampleResponse);
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

    org.mockito.Mockito.when(this.responseServiceMock.update(exampleResponse2))
        .thenReturn(exampleResponse2);
    assertEquals(exampleResponse2,
        this.responseControllerImpl.updateResponse(exampleResponse2, -11));
    System.out.println("exampleResponse1: " + exampleResponse1);
    System.out.println("exampleResponse2: " + exampleResponse2); // Should match number above
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

    org.mockito.Mockito.when(this.responseServiceMock.getById(-33))
        .thenThrow(new ResponseNotFoundException(null));
    assertEquals(null, this.responseControllerImpl.updateResponse(exampleResponse3, -33));
    System.out.println("exampleResponse3: " + exampleResponse3);
  }

  /**
   * Tests if CreateOrUpdate results in a response being created or updated
   */
  @Test
  public void TestCreateOrUpdate() {
    Response exampleResponse4 = new Response();
    exampleResponse4.setBody("I'm here 4");
    exampleResponse4.setId(-44);

    org.mockito.Mockito.when(this.responseServiceMock.createOrUpdate(exampleResponse4))
        .thenReturn(exampleResponse4);
    assertEquals(exampleResponse4,
        this.responseControllerImpl.createOrUpdate(exampleResponse4, -44));
    System.out.println("exampleResponse4: " + exampleResponse4);
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
    assertEquals(listResponses, this.responseControllerImpl.getAllResponses());
    System.out.println("listResponses: " + listResponses);
  }

}

