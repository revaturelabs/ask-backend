package com.revaturelabs.ask.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.revaturelabs.ask.image.Image;
import com.revaturelabs.ask.question.Question;
import com.revaturelabs.ask.tag.Tag;

public class ImageModelTest {

	@Test
	public void imageJavaBeanTest() {
		Image testImage = new Image();
		Question imAQuestion = new Question();
		testImage.setId(10);
		byte[] byteArr = new byte[1];
		byteArr[0] = '1';
		testImage.setImage(byteArr);
		testImage.setQuestion(imAQuestion);
		assertNotNull(testImage);
		Image testImageSame = new Image(10, byteArr, imAQuestion);

		// getters
		assertEquals(testImageSame, testImage);
		assertEquals(testImage, new Image(10, byteArr, imAQuestion));
		assertEquals(testImage.getId(), 10);
		assertEquals(testImage.getImage(), byteArr);
		assertEquals(testImage.getQuestion(), imAQuestion);

		// .equals()
		Image testImageNull = null;
		Tag testTag = new Tag();
		Image testImageDiffId = new Image(11, byteArr, imAQuestion);
		byte[] byteArr2 = new byte[1];
		byteArr2[0] = '2';
		Image testImageDiffImage = new Image(10, byteArr2, imAQuestion);
		assertTrue(testImage.equals(testImage));
		assertFalse(testImage.equals(testImageNull));
		assertFalse(testImage.equals(testTag));
		assertFalse(testImage.equals(testImageDiffId));
		assertFalse(testImage.equals(testImageDiffImage));

		// .toString()
		assertEquals(testImageSame.toString(),
				"Image [id=10, image=[49], question=" + testImageSame.getQuestion().toString() + "]");
	}
}
