package com.revaturelabs.ask.services;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import com.revaturelabs.ask.tag.Tag;
import com.revaturelabs.ask.tag.TagConflictException;
import com.revaturelabs.ask.tag.TagController;
import com.revaturelabs.ask.tag.TagNotFoundException;
import com.revaturelabs.ask.tag.TagRepository;
import com.revaturelabs.ask.tag.TagService;
import com.revaturelabs.ask.tag.TagServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TagServiceTest {

	@Test
	public void contextLoads() {
	}

	static ServiceMockData mockData;

	@Mock
	TagRepository tagRepositoryMock;

	@Mock
	TagService tagServiceMock;

	@InjectMocks
	TagService tagServiceImpl = new TagServiceImpl();

	@InjectMocks
	TagController tagControllerImpl = new TagController();

	/**
	 * Test of tagService findAll method.
	 * 
	 */
	@Test
	public void getAllTagsTest() {

		when((tagRepositoryMock.findAll())).thenReturn(ServiceMockData.tagReturnList);

		assertEquals(ServiceMockData.tagReturnList, tagServiceImpl.getAll());
	}

	/**
	 * Test retrieval of getting one tag
	 * 
	 */
	@Test
	public void getOneTagTest() {

		when(tagRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testTag1));

		assertEquals(ServiceMockData.testTag1, tagServiceImpl.getById(1));
	}

	/**
	 * Test failure of getting a non-existent tag
	 * 
	 */
	@Test(expected = TagNotFoundException.class)
	public void getBadTagTest() {

		when(tagRepositoryMock.findById(10)).thenReturn(Optional.empty());

		tagServiceImpl.getById(10);
	}

	/**
	 * Test accuracy of getting a tag
	 * 
	 */
	@Test
	public void getTagByIdAccuracyTest() {
		when(tagRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testTag1));

		assertNotEquals(ServiceMockData.testTag2, tagServiceImpl.getById(1));
	}

	/**
	 * Test retrieval of an existing tag by name
	 * 
	 */
	@Test
	public void getTagByNameTest() {

		when(tagRepositoryMock.findAll()).thenReturn(ServiceMockData.tagReturnList);

		assertEquals(ServiceMockData.testTag1, tagServiceImpl.getTagByName("Test 1"));
	}

	/**
	 * Test failure retrieval of a bad tag name
	 * 
	 */
	@Test(expected = TagNotFoundException.class)
	public void getBadTagNameTest() {
		when(tagRepositoryMock.findAll()).thenReturn(ServiceMockData.tagReturnList);
		tagServiceImpl.getTagByName("notTestTag1");
	}

	/**
	 * Test accuracy of getting a tag by name
	 * 
	 */
	@Test
	public void getAccurateTagNameTest() {
		when(tagRepositoryMock.findAll()).thenReturn(ServiceMockData.tagReturnList);

		assertNotEquals(ServiceMockData.testTag2, tagServiceImpl.getTagByName("Test 1"));
	}

	/**
	 * Test tag creation
	 * 
	 */
	@Test
	public void tagCreationAccuracyTest() {
		when(tagRepositoryMock.save(ServiceMockData.testTag1)).thenReturn(ServiceMockData.testTag1PostCreate);

		assertEquals(ServiceMockData.testTag1PostCreate, tagServiceImpl.create(ServiceMockData.testTag1));
	}

	/**
	 * Test updating a tag
	 */
	@Test
	public void tagUpdateTest() {
		Tag testTag1UpdateInfo = new Tag();
		testTag1UpdateInfo.setId(0);
		testTag1UpdateInfo.setName("New name");

		when(tagRepositoryMock.findById(testTag1UpdateInfo.getId())).thenReturn(Optional.of(ServiceMockData.testTag1));
		when(tagRepositoryMock.save(testTag1UpdateInfo)).thenReturn(ServiceMockData.testTag1);

		assertEquals(ServiceMockData.testTag1, tagServiceImpl.update(testTag1UpdateInfo));
	}

	/**
	 * Test updating failure for non-existent tag
	 * 
	 */
	@Test(expected = TagNotFoundException.class)
	public void tagUpdateNonExistantFailureTest() {
		Tag nonExistentTag = new Tag();
		nonExistentTag.setId(5);

		when(tagRepositoryMock.findById(5)).thenReturn(Optional.empty());

		tagServiceImpl.update(nonExistentTag);
	}

	/**
	 * Testing data integrity violation failure for updating a tag
	 * 
	 */
	@Test(expected = TagConflictException.class)
	public void tagFailureToUpdateTest() {
		when(tagRepositoryMock.findById(0)).thenReturn(Optional.of(ServiceMockData.testTag1));
		when(tagRepositoryMock.save(ServiceMockData.testTag1)).thenThrow(DataIntegrityViolationException.class);

		tagServiceImpl.update(ServiceMockData.testTag1);
	}

	/**
	 * Test create or update
	 * 
	 */
	@Test
	public void createOrUpdateTagTest() {
		Tag nonExistentTag = new Tag();
		nonExistentTag.setId(4);
		nonExistentTag.setName("New tag");
		List<Tag> tagReturnList = new ArrayList<Tag>();
		tagReturnList.addAll(Arrays.asList(ServiceMockData.testTag1, ServiceMockData.testTag2, ServiceMockData.testTag3, nonExistentTag));

		when(tagRepositoryMock.save(nonExistentTag)).thenReturn(nonExistentTag);
		when(tagRepositoryMock.findAll()).thenReturn(tagReturnList);

		tagServiceImpl.createOrUpdate(nonExistentTag);
		assertEquals(tagReturnList, tagServiceImpl.getAll());
	}

	/**
	 * Testing data integrity violation failure for creating or updating a tag
	 * 
	 */
	@Test(expected = TagConflictException.class)
	public void tagFailureToCreateOrUpdateTest() {
		when(tagRepositoryMock.save(ServiceMockData.testTag1)).thenThrow(DataIntegrityViolationException.class);

		tagServiceImpl.createOrUpdate(ServiceMockData.testTag1);
	}

	/**
	 * Test deletion of a tag
	 * 
	 */
	@Test
	public void tagDeletionTest() {
		when(tagRepositoryMock.existsById(1)).thenReturn(true);
		Mockito.doNothing().when(tagRepositoryMock).deleteById(1);
		tagServiceImpl.delete(1);
		when(tagRepositoryMock.existsById(1)).thenReturn(false);

		Assert.assertFalse(tagRepositoryMock.existsById(1));
	}

	/**
	 * Test exception throw of invalid tag
	 */
	@Test(expected = TagNotFoundException.class)
	public void tagDeleteNonExistantTagTest() {
		when(tagRepositoryMock.existsById(10)).thenReturn(false);
		Mockito.doNothing().when(tagRepositoryMock).deleteById(10);

		tagServiceImpl.delete(10);
	}

	/**
	 * Test retrieval of valid tags
	 * 
	 */
	@Test
	public void tagGetValidTagsTest() {
		when(tagRepositoryMock.findAll()).thenReturn(ServiceMockData.tagReturnList);
		Set<Tag> searchTagsSet = new HashSet<Tag>();
		searchTagsSet.add(ServiceMockData.testTag1);
		searchTagsSet.add(ServiceMockData.testTag2);

		assertEquals(searchTagsSet, tagServiceImpl.getValidTags(searchTagsSet));
	}

	/**
	 * Test retrieval of no tags
	 * 
	 */
	@Test
	public void getNoValidTagsTest() {
		when(tagRepositoryMock.findAll()).thenReturn(ServiceMockData.tagReturnList);

		assertEquals(new HashSet<Tag>(), tagServiceImpl.getValidTags(null));
	}

	/**
	 * Test retrieval of invalid tags
	 */
	@Test(expected = TagNotFoundException.class)
	public void getInvalidTagsFailureTest() {
		when(tagRepositoryMock.findAll()).thenReturn(ServiceMockData.tagReturnList);

		Tag invalidTag = new Tag();
		invalidTag.setId(20);
		invalidTag.setName("Invalid tag");

		HashSet<Tag> associatedTags = new HashSet<Tag>();
		associatedTags.add(invalidTag);

		tagServiceImpl.getValidTags(associatedTags);
	}

	/**
	 * Test retrieval of invalid tags with at least one invalid tag and one valid
	 * tag
	 * 
	 */
	@Test(expected = TagNotFoundException.class)
	public void getInvalidTagsWithAtLeastOneValidTagFailureTest() {
		when(tagRepositoryMock.findAll()).thenReturn(ServiceMockData.tagReturnList);

		Tag invalidTag = new Tag();
		invalidTag.setId(20);
		invalidTag.setName("Invalid tag");

		HashSet<Tag> associatedTags = new HashSet<Tag>();
		associatedTags.add(invalidTag);
		associatedTags.add(ServiceMockData.testTag1);

		tagServiceImpl.getValidTags(associatedTags);
	}
}