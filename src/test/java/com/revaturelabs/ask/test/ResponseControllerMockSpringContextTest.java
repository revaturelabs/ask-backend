package com.revaturelabs.ask.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.revaturelabs.ask.response.Response;
import com.revaturelabs.ask.response.ResponseController;
import com.revaturelabs.ask.response.ResponseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResponseControllerMockSpringContextTest {


  @MockBean
  ResponseService responseServiceMock;

  @Autowired
  ResponseController responseControllerImpl;

  @Test
  public void testGetResponseById() {

    Response exampleResponse = new Response();
    when((responseServiceMock.getById(1))).thenReturn(exampleResponse);

    assertEquals(exampleResponse, responseControllerImpl.getResponseById(1));
  }
}
