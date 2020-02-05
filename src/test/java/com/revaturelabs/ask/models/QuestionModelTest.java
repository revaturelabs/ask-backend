package com.revaturelabs.ask.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.revaturelabs.ask.image.Image;
import com.revaturelabs.ask.tag.Tag;

public class QuestionModelTest {
	
	static ModelMockData mockData;
	
	@Test
	public void testModelEqualsAndHashCode() {
		assertTrue(ModelMockData.testQuestion1.hashCode() == ModelMockData.testQuestion2.hashCode());
		assertTrue(ModelMockData.testQuestion1.hashCode() != ModelMockData.nullQuestion.hashCode());
		assertTrue(ModelMockData.emptyQuestion == null);

		assertNotNull(ModelMockData.testQuestion1.toString());

		assertTrue(ModelMockData.testQuestion1.equals(ModelMockData.testQuestion2) & ModelMockData.testQuestion2.equals(ModelMockData.testQuestion1));
		assertTrue(ModelMockData.nullQuestion.equals(ModelMockData.nullQuestion2) & ModelMockData.nullQuestion2.equals(ModelMockData.nullQuestion));
		assertFalse(ModelMockData.testQuestion1.equals(ModelMockData.testQuestion3) & ModelMockData.testQuestion3.equals(ModelMockData.testQuestion1));
		assertFalse(ModelMockData.testQuestion1.equals(ModelMockData.testQuestion4) & ModelMockData.testQuestion4.equals(ModelMockData.testQuestion1));
		assertFalse(ModelMockData.testQuestion1.equals(ModelMockData.testQuestion5) & ModelMockData.testQuestion5.equals(ModelMockData.testQuestion1));
		assertFalse(ModelMockData.testQuestion1.equals(ModelMockData.testQuestion6) & ModelMockData.testQuestion6.equals(ModelMockData.testQuestion1));
		assertFalse(ModelMockData.testQuestion1.equals(ModelMockData.testQuestion7) & ModelMockData.testQuestion7.equals(ModelMockData.testQuestion1));
		assertFalse(ModelMockData.testQuestion1.equals(ModelMockData.testQuestion8) & ModelMockData.testQuestion8.equals(ModelMockData.testQuestion1));
		assertFalse(ModelMockData.testQuestion1.equals(ModelMockData.testQuestion9) & ModelMockData.testQuestion9.equals(ModelMockData.testQuestion1));
		assertFalse(ModelMockData.testQuestion1.equals(ModelMockData.testQuestion10) & ModelMockData.testQuestion10.equals(ModelMockData.testQuestion1));
		assertFalse(ModelMockData.testQuestion1.equals(ModelMockData.testQuestion11) & ModelMockData.testQuestion11.equals(ModelMockData.testQuestion1));
		assertFalse(ModelMockData.testQuestion1.equals(ModelMockData.nullQuestion) & ModelMockData.nullQuestion.equals(ModelMockData.testQuestion1));

		assertTrue(ModelMockData.testQuestion1.getAssociatedTags() != null & ModelMockData.testQuestion2.getAssociatedTags() != null);
		assertTrue(ModelMockData.testQuestion1.getBody() != null & ModelMockData.testQuestion4.getBody() == null);
		assertTrue(ModelMockData.testQuestion1.getHead() == ModelMockData.testQuestion4.getHead() & ModelMockData.testQuestion1.getHead() != null);
		assertTrue(ModelMockData.testQuestion1.getHighlightedResponseId() == 1 & ModelMockData.testQuestion8.getHighlightedResponseId() == null);
		assertTrue(ModelMockData.testQuestion1.getId() == 1 & ModelMockData.testQuestion3.getId() == null);
		assertTrue(ModelMockData.testQuestion1.getQuestionerId() == 1 & ModelMockData.testQuestion6.getQuestionerId() == null);
		assertTrue(ModelMockData.testQuestion1.getResponses() != null & ModelMockData.testQuestion7.getResponses() == null);
		assertTrue(ModelMockData.testQuestion1.getCreationDate() != null & ModelMockData.testQuestion11.getCreationDate() == null);
		assertTrue(ModelMockData.testQuestion1.getImages() != null & ModelMockData.testQuestion10.getImages() == null);
		assertTrue(ModelMockData.testQuestion1.getUser() == null);
		assertTrue(ModelMockData.testQuestion1.getClass() == ModelMockData.nullQuestion.getClass());
		assertFalse(ModelMockData.testQuestion1.equals(ModelMockData.emptyQuestion));
		assertFalse(ModelMockData.testQuestion1.equals(ModelMockData.testDate));

		Tag newTag = new Tag();

		ModelMockData.nullQuestion.addImageToImages(ModelMockData.testImage1);
		ModelMockData.nullQuestion.setAssociatedTags(null);
		ModelMockData.nullQuestion.addTagToQuestion(newTag);

		Image newImage = new Image();
		ModelMockData.testQuestion1.addImageToImages(newImage);
		ModelMockData.nullQuestion.addTagToQuestion(newTag);

		assertTrue(ModelMockData.nullQuestion.getImages() != null);
		assertTrue(ModelMockData.nullQuestion.getAssociatedTags() != null);
	}

}
