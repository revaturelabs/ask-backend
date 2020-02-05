package com.revaturelabs.ask.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Date;
import org.junit.Test;
import com.revaturelabs.ask.response.Response;

public class ResponseModelTest {

  static ModelMockData mockData;

  @Test
  public void responseJavaBeanTest() {
    Response testResponse = new Response();
    testResponse.setId(10);
    testResponse.setBody("Response Body");
    testResponse.setResponderId(10);
    Date date = new Date();
    testResponse.setCreationDate(date);
    testResponse.setQuestionId(10);
    testResponse.setQuestion(ModelMockData.testQuestion1);
    testResponse.setUser(ModelMockData.testUser1);

    Response testResponseSame = new Response();
    testResponseSame.setId(10);
    testResponseSame.setBody("Response Body");
    testResponseSame.setResponderId(10);
    testResponseSame.setCreationDate(date);
    testResponseSame.setQuestionId(10);
    testResponseSame.setQuestion(ModelMockData.testQuestion1);
    testResponseSame.setUser(ModelMockData.testUser1);

    Response testResponseNullFields = new Response();
    testResponseNullFields.setId(null);
    testResponseNullFields.setBody(null);
    testResponseNullFields.setResponderId(null);
    testResponseNullFields.setCreationDate(null);
    testResponseNullFields.setQuestionId(null);
    testResponseNullFields.setQuestion(ModelMockData.testQuestion1);
    testResponseNullFields.setUser(ModelMockData.testUser1);

    Response testResponseNullFieldsSame = new Response();
    testResponseNullFieldsSame.setId(null);
    testResponseNullFieldsSame.setBody(null);
    testResponseNullFieldsSame.setResponderId(null);
    testResponseNullFieldsSame.setCreationDate(null);
    testResponseNullFieldsSame.setQuestionId(null);
    testResponseNullFieldsSame.setQuestion(ModelMockData.testQuestion1);
    testResponseNullFieldsSame.setUser(ModelMockData.testUser1);

    Response testResponseDiffId = new Response();
    testResponseDiffId.setId(11);
    testResponseDiffId.setBody("Response Body");
    testResponseDiffId.setResponderId(10);
    testResponseDiffId.setCreationDate(date);
    testResponseDiffId.setQuestionId(10);
    testResponseDiffId.setQuestion(ModelMockData.testQuestion1);
    testResponseDiffId.setUser(ModelMockData.testUser1);

    Response testResponseNullId = new Response();
    testResponseNullId.setId(null);
    testResponseNullId.setBody("Response Body");
    testResponseNullId.setResponderId(10);
    testResponseNullId.setCreationDate(date);
    testResponseNullId.setQuestionId(10);
    testResponseNullId.setQuestion(ModelMockData.testQuestion1);
    testResponseNullId.setUser(ModelMockData.testUser1);

    Response testResponseDiffBody = new Response();
    testResponseDiffBody.setId(10);
    testResponseDiffBody.setBody("Different Response Body");
    testResponseDiffBody.setResponderId(10);
    testResponseDiffBody.setCreationDate(date);
    testResponseDiffBody.setQuestionId(10);
    testResponseDiffBody.setQuestion(ModelMockData.testQuestion1);
    testResponseDiffBody.setUser(ModelMockData.testUser1);

    Response testResponseNullBody = new Response();
    testResponseNullBody.setId(10);
    testResponseNullBody.setBody(null);
    testResponseNullBody.setResponderId(10);
    testResponseNullBody.setCreationDate(date);
    testResponseNullBody.setQuestionId(10);
    testResponseNullBody.setQuestion(ModelMockData.testQuestion1);
    testResponseNullBody.setUser(ModelMockData.testUser1);

    Response testResponseDiffResponderId = new Response();
    testResponseDiffResponderId.setId(10);
    testResponseDiffResponderId.setBody("Response Body");
    testResponseDiffResponderId.setResponderId(11);
    testResponseDiffResponderId.setCreationDate(date);
    testResponseDiffResponderId.setQuestionId(10);
    testResponseDiffResponderId.setQuestion(ModelMockData.testQuestion1);
    testResponseDiffResponderId.setUser(ModelMockData.testUser1);

    Response testResponseNullResponderId = new Response();
    testResponseNullResponderId.setId(10);
    testResponseNullResponderId.setBody("Response Body");
    testResponseNullResponderId.setResponderId(null);
    testResponseNullResponderId.setCreationDate(date);
    testResponseNullResponderId.setQuestionId(10);
    testResponseNullResponderId.setQuestion(ModelMockData.testQuestion1);
    testResponseNullResponderId.setUser(ModelMockData.testUser1);

    Response testResponseDiffCreationDate = new Response();
    testResponseDiffCreationDate.setId(10);
    testResponseDiffCreationDate.setBody("Response Body");
    testResponseDiffCreationDate.setResponderId(10);
    testResponseDiffCreationDate.setCreationDate(new Date(111L));
    testResponseDiffCreationDate.setQuestionId(10);
    testResponseDiffCreationDate.setQuestion(ModelMockData.testQuestion1);
    testResponseDiffCreationDate.setUser(ModelMockData.testUser1);

    Response testResponseNullCreationDate = new Response();
    testResponseNullCreationDate.setId(10);
    testResponseNullCreationDate.setBody("Response Body");
    testResponseNullCreationDate.setResponderId(10);
    testResponseNullCreationDate.setCreationDate(null);
    testResponseNullCreationDate.setQuestionId(10);
    testResponseNullCreationDate.setQuestion(ModelMockData.testQuestion1);
    testResponseNullCreationDate.setUser(ModelMockData.testUser1);

    Response testResponseDiffQuestionId = new Response();
    testResponseDiffQuestionId.setId(10);
    testResponseDiffQuestionId.setBody("Response Body");
    testResponseDiffQuestionId.setResponderId(10);
    testResponseDiffQuestionId.setCreationDate(date);
    testResponseDiffQuestionId.setQuestionId(11);
    testResponseDiffQuestionId.setQuestion(ModelMockData.testQuestion1);
    testResponseDiffQuestionId.setUser(ModelMockData.testUser1);

    Response testResponseNullQuestionId = new Response();
    testResponseNullQuestionId.setId(10);
    testResponseNullQuestionId.setBody("Response Body");
    testResponseNullQuestionId.setResponderId(10);
    testResponseNullQuestionId.setCreationDate(date);
    testResponseNullQuestionId.setQuestionId(null);
    testResponseNullQuestionId.setQuestion(ModelMockData.testQuestion1);
    testResponseNullQuestionId.setUser(ModelMockData.testUser1);

    // Response Exists
    assertNotNull(testResponse);

    // Test Getters and Setters
    assertEquals(testResponse.getId(), 10);
    assertEquals(testResponse.getBody(), "Response Body");
    assertEquals(testResponse.getResponderId(), (Integer) 10);
    assertEquals(testResponse.getCreationDate(), date);
    assertEquals(testResponse.getQuestionId(), (Integer) 10);
    assertEquals(testResponse.getQuestion(), ModelMockData.testQuestion1);
    assertEquals(testResponse.getUser(), ModelMockData.testUser1);

    // .hashcode()
    assertTrue(testResponse.hashCode() == testResponseSame.hashCode());
    assertFalse(testResponse.hashCode() == testResponseNullFields.hashCode());

    // .equals()
    assertTrue(testResponse.equals(testResponseSame));
    assertTrue(testResponseNullFields.equals(testResponseNullFieldsSame));
    assertFalse(testResponse.equals(null));
    assertFalse(testResponse.equals(ModelMockData.testTag1));
    assertFalse(testResponseDiffBody.equals(testResponseNullFields)
        & testResponseDiffBody.equals(testResponse));
    assertFalse(testResponseNullBody.equals(testResponseNullFields)
        & testResponseNullBody.equals(testResponse));
    assertFalse(testResponseNullId.equals(testResponseNullFields)
        & testResponseNullId.equals(testResponse));
    assertFalse(testResponseDiffId.equals(testResponseNullFields)
        & testResponseDiffId.equals(testResponse));
    assertFalse(testResponseNullId.equals(testResponseNullFields)
        & testResponseNullId.equals(testResponse));
    assertFalse(testResponseDiffCreationDate.equals(testResponseNullFields)
        & testResponseDiffCreationDate.equals(testResponse));
    assertFalse(testResponseNullCreationDate.equals(testResponseNullFields)
        & testResponseNullCreationDate.equals(testResponse));
    assertFalse(testResponseDiffQuestionId.equals(testResponseNullFields)
        & testResponseDiffQuestionId.equals(testResponse));
    assertFalse(testResponseNullQuestionId.equals(testResponseNullFields)
        & testResponseNullQuestionId.equals(testResponse));
    assertFalse(testResponseDiffResponderId.equals(testResponseNullFields)
        & testResponseDiffResponderId.equals(testResponse));
    assertFalse(testResponseNullResponderId.equals(testResponseNullFields)
        & testResponseNullResponderId.equals(testResponse));
  }
}
