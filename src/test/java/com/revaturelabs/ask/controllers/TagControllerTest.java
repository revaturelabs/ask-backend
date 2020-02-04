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
import com.revaturelabs.ask.tag.Tag;
import com.revaturelabs.ask.tag.TagConflictException;
import com.revaturelabs.ask.tag.TagController;
import com.revaturelabs.ask.tag.TagNotFoundException;
import com.revaturelabs.ask.tag.TagRepository;
import com.revaturelabs.ask.tag.TagService;

/**
 * @author Bryan Ritter, David Blitz, Efrain Vila
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TagControllerTest {

	@Test
	public void contextLoads() {
	}

	@MockBean
	TagRepository tagRepositoryMock;

	@MockBean
	TagService tagServiceMock;

	@Autowired
	TagController tagControllerImpl;

	@Autowired
	TagService tagServiceImpl;

	/**
	 * Tests for tags.
	 */

	/**
	 * Test getTagById returns a tag from the service layer
	 */
	@Test
	public void testGetTagById() {
		Tag exampleTag = new Tag();
		exampleTag.setId(1);
		exampleTag.setName("Java");
		when((tagServiceMock.getById(1))).thenReturn(exampleTag);
		assertEquals(ResponseEntity.ok(exampleTag), tagControllerImpl.getTagById(1));
	}

	@Test(expected = ResponseStatusException.class)
	public void testFailedGetTagById() {
		when((tagServiceMock.getById(1))).thenThrow(TagNotFoundException.class);
		tagControllerImpl.getTagById(1);
	}

	/**
	 * Test that getAllTags in the controller returns a list of tags
	 */
	@Test
	public void testGetAllTags() {
		Tag javaScriptTag = new Tag();
		javaScriptTag.setName("JavaScript");
		Tag javaTag = new Tag();
		javaTag.setName("Java");
		List<Tag> exampleTags = new ArrayList<Tag>();
		exampleTags.add(javaScriptTag);
		exampleTags.add(javaTag);
		when((tagServiceMock.getAll())).thenReturn(exampleTags);
		assertEquals(ResponseEntity.ok(exampleTags), tagControllerImpl.getAllTags());
	}

	/**
	 * That that the createTag method returns the new tag created
	 */
	@Test
	public void testCreateTag() {
		Tag exampleTag = new Tag();
		exampleTag.setName("JavaScript");
		when((tagServiceMock.create(exampleTag))).thenReturn(exampleTag);
		assertEquals(exampleTag, tagControllerImpl.createTag(exampleTag));
	}

	/**
	 * Test that updateTag returns the updated tag
	 */
	@Test
	public void testUpdateTag() {
		Tag exampleTag = new Tag();
		exampleTag.setId(1);
		exampleTag.setName("JavaScript");

		when((tagServiceMock.update(exampleTag))).thenReturn(exampleTag);
		assertEquals(exampleTag, tagControllerImpl.updateTag(exampleTag, 1));
	}

	/**
	 * Test deleting tag.
	 */
	@Test
	public void testDeleteTag() {
		Tag exampleTag = new Tag();
		exampleTag.setId(1);
		exampleTag.setName("JavaScript");

		when((tagServiceMock.create(exampleTag))).thenReturn(exampleTag);
		tagServiceMock.create(exampleTag);
		doNothing().when(tagServiceMock).delete(exampleTag.getId());
		tagServiceMock.delete(exampleTag.getId());
		when((tagServiceMock.getById(exampleTag.getId()))).thenReturn(null);
		tagControllerImpl.deleteTag(exampleTag.getId());
		Assert.assertNull(tagServiceMock.getById(exampleTag.getId()));
	}

	@Test(expected = ResponseStatusException.class)
	public void testFailedDelete() {
		doThrow(TagNotFoundException.class).when(tagServiceMock).delete(1);
		tagControllerImpl.deleteTag(1);
	}

	/**
	 * Test that createOrUpdate returns the tag to be created/updated
	 */
	@Test
	public void testCreateUpdateTag() {
		Tag exampleTag = new Tag();
		exampleTag.setName("JavaScript");
		when((tagServiceMock.createOrUpdate(exampleTag))).thenReturn(exampleTag);
		assertEquals(exampleTag, tagControllerImpl.createOrUpdate(exampleTag, 1));
	}

	@Test(expected = ResponseStatusException.class)
	public void testFailedCreateUpdateTag() {
		Tag exampleTag = new Tag();
		exampleTag.setName("JavaScript");
		when((tagServiceMock.createOrUpdate(exampleTag))).thenThrow(TagConflictException.class);
		tagControllerImpl.createOrUpdate(exampleTag, 1);
	}

	/**
	 * Test a failed tag update would return a null tag.
	 */
	@Test
	public void testUpdateTagFails() {
		Tag exampleTag = new Tag();
		exampleTag.setName("JavaScript");
		exampleTag.setId(1);
		Tag exampleTag2 = new Tag();
		exampleTag2.setName("Java");
		when(this.tagServiceMock.update(exampleTag)).thenReturn(null);
		assertEquals(null, tagControllerImpl.updateTag(exampleTag, 5));
	}

	@Test(expected = ResponseStatusException.class)
	public void testUpdateTagTagConflict() {
		Tag exampleTag = new Tag();
		exampleTag.setName("JavaScript");
		exampleTag.setId(1);
		when(this.tagServiceMock.update(exampleTag)).thenThrow(TagConflictException.class);
		tagControllerImpl.updateTag(exampleTag, 5);
	}

	@Test(expected = ResponseStatusException.class)
	public void testUpdateTagTagNotFound() {
		Tag exampleTag = new Tag();
		exampleTag.setName("JavaScript");
		exampleTag.setId(1);
		when(this.tagServiceMock.update(exampleTag)).thenThrow(TagNotFoundException.class);
		tagControllerImpl.updateTag(exampleTag, 5);
	}
}
