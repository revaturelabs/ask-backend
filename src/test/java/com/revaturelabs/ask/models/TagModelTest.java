package com.revaturelabs.ask.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.revaturelabs.ask.image.Image;
import com.revaturelabs.ask.tag.Tag;

public class TagModelTest {

  @Test
  public void tagHashcodeEqualsStringTest() {
    Tag testTag = new Tag();
    testTag.setId(10);
    testTag.setName("tagName");

    Tag testTagSame = new Tag();
    testTagSame.setId(10);
    testTagSame.setName("tagName");

    Tag testTagDiffId = new Tag();
    testTagDiffId.setId(11);
    testTagDiffId.setName("tagName");

    Tag testTagDiffName = new Tag();
    testTagDiffName.setId(10);
    testTagDiffName.setName("tagName2");

    Tag testTagNullId = new Tag();
    testTagNullId.setId(null);
    testTagNullId.setName("tagName");

    Tag testTagNullIdSame = new Tag();
    testTagNullIdSame.setId(null);
    testTagNullIdSame.setName("tagName");

    Tag testTagNullName = new Tag();
    testTagNullName.setId(10);
    testTagNullName.setName(null);

    Tag testTagNullNameSame = new Tag();
    testTagNullNameSame.setId(10);
    testTagNullNameSame.setName(null);

    Tag testTagNullFields = new Tag();
    testTagNullFields.setId(null);
    testTagNullFields.setName(null);

    Tag testTagNullObj = null;

    Image imageNotTag = new Image();

    // .equals()
    assertTrue(testTag.equals(testTagSame) & testTagSame.equals(testTag));
    assertFalse(testTag.equals(testTagNullId) & testTagNullId.equals(testTag));
    assertFalse(testTag.equals(testTagNullName) & testTagNullName.equals(testTag));
    assertFalse(testTag.equals(testTagNullObj));
    assertFalse(testTag.equals(imageNotTag));
    assertFalse(testTag.equals(testTagDiffName) & testTagDiffName.equals(testTag));
    assertFalse(testTag.equals(testTagDiffId) & testTagDiffId.equals(testTag));
    assertTrue(testTagNullId.equals(testTagNullIdSame));
    assertTrue(testTagNullName.equals(testTagNullNameSame));

    // .hashCode()
    assertTrue(testTag.hashCode() == testTagSame.hashCode());
    assertFalse(testTag.hashCode() == testTagNullFields.hashCode());

    // .toString()
    assertEquals(testTag.toString(), "Tag [id=10, name=tagName]");
  }
}
