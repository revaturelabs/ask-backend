package com.revaturelabs.ask;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
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
import com.revaturelabs.ask.response.ResponseService;
import com.revaturelabs.ask.tags.Tag;
import com.revaturelabs.ask.tags.TagController;
import com.revaturelabs.ask.tags.TagService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AskApplicationTests {

  @Test
  public void contextLoads() {}


  @MockBean
  ResponseService responseServiceMock;

  @MockBean
  TagService tagServiceMock;

  @Autowired
  ResponseController responseControllerImpl;

  @Autowired
  TagController tagControllerImpl;

  @Test
  public void testGetResponseById() {

    Response exampleResponse = new Response();
    when((responseServiceMock.getById(1))).thenReturn(exampleResponse);

    assertEquals(exampleResponse, responseControllerImpl.getResponseById(1));
  }

  @Test
  public void testGetTagById() {
    Tag exampleTag = new Tag();
    when((tagServiceMock.getById(1))).thenReturn(exampleTag);
    assertEquals(exampleTag, tagControllerImpl.getTagById(1));
  }

  @Test
  public void testGetAllTags() {
    Tag javaScriptTag = new Tag();
    javaScriptTag.setName("JavaScript");
    Tag javaTag = new Tag();
    javaTag.setName("Java");
    List<Tag> exampleTags = new ArrayList();
    exampleTags.add(javaScriptTag);
    exampleTags.add(javaTag);
    when((tagServiceMock.getAll())).thenReturn(exampleTags);
    assertEquals(exampleTags, tagControllerImpl.getAllTags());
  }

  @Test
  public void testCreate() {
    Tag exampleTag = new Tag();
    exampleTag.setName("JavaScript");
    when((tagServiceMock.create(exampleTag))).thenReturn(exampleTag);
    assertEquals(exampleTag, tagControllerImpl.createTag(exampleTag));
  }

  @Test
  public void testUpdate() {
    Tag exampleTag = new Tag();
    exampleTag.setId(1);
    exampleTag.setName("JavaScript");

    Tag exampleTag2 = new Tag();
    exampleTag2.setName("JScript");

    when((tagServiceMock.update(exampleTag))).thenReturn(exampleTag);
    assertEquals(exampleTag, tagControllerImpl.updateTag(exampleTag, 1));
  }

  @Test
  public void testCreateUpdate() {
    Tag exampleTag = new Tag();
    exampleTag.setName("JavaScript");
    when((tagServiceMock.createOrUpdate(exampleTag))).thenReturn(exampleTag);
    assertEquals(exampleTag, tagControllerImpl.createOrUpdate(exampleTag, 1));
  }


}
