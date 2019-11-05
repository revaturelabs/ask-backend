package com.revaturelabs.ask;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.revaturelabs.ask.tag.Tag;
import com.revaturelabs.ask.tag.TagService;
import com.revaturelabs.ask.user.User;
import com.revaturelabs.ask.user.UserController;
import com.revaturelabs.ask.user.UserService;
import com.revaturelabs.ask.user.UserConflictException;
import com.revaturelabs.ask.user.UserNotFoundException;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AskApplicationUsersTest {

  @Test
  public void contextLoads() {}


  @MockBean
  UserService userServiceMock;
  
  @MockBean
  TagService tagServiceMock;

  @Autowired
  UserController userControllerImpl;

  @Test
  public void testGettingUserById() {

    int index = 4;
    User exampleUser = new User();
    
    when((userServiceMock.findById(index))).thenReturn(exampleUser);
    
    assertEquals(exampleUser, userControllerImpl.findById(index));
  }
  
  @Test
  public void testGettingAllUsers() {

    List<User> exampleUsers = new ArrayList<User>();
    when((userServiceMock.findAll())).thenReturn(exampleUsers);

    assertEquals(exampleUsers, userControllerImpl.findAll());
  }
  @Test(expected = UserConflictException.class)
  public void testUpdatingUser() {

    int index = 1; // the last object in the database so far.
    User exampleUser = new User();
    
    exampleUser.setId(index);
    exampleUser.setUsername("retweet");
    exampleUser.setPassword("weguiawej");
    exampleUser.setExpert(false);
    userServiceMock.create(exampleUser);
    
    when((userServiceMock.findById(index))).thenReturn(exampleUser);
    
    exampleUser.setId(index);
    exampleUser.setUsername("retweet");
    exampleUser.setPassword("weguiawej");
    exampleUser.setExpert(false);
    
    when((userServiceMock.update(exampleUser))).thenThrow(UserConflictException.class);
    
    //actual update
    System.out.println(exampleUser);
    exampleUser.setUsername("blahblahblah");
    exampleUser.setPassword("djfjgjofoo");
    exampleUser.setExpert(true);
    
    when((userServiceMock.update(exampleUser))).thenReturn(exampleUser);

    System.out.println(exampleUser);
    
    assertEquals(exampleUser, userControllerImpl.findById(index));
  }
  @Test
  public void testAddingUserTags() {
    
    int index = 1; // the last object in the database so far.
    User exampleUser = new User();
    Tag[] sampleTags = new Tag[2];
    
    for (int a = 0; a < sampleTags.length; ++a) {
      sampleTags[a] = new Tag();
    }
    
    sampleTags[0].setId(index);
    sampleTags[0].setName("JS");
    
    sampleTags[1].setId(index+1);
    sampleTags[1].setName("Java");
    
    
    when((userServiceMock.findById(index))).thenReturn(exampleUser);
    System.out.println(exampleUser);
    exampleUser.setUsername("blahblahblah");
    exampleUser.setId(index);
    exampleUser.setExpert(true);

    Set<Tag> replaceTag = new HashSet<Tag>();
    for (int a = 0; a < sampleTags.length; ++a) {
      replaceTag.add(sampleTags[a]);
    }
    System.out.println(replaceTag);
    exampleUser.setUserTags(replaceTag);
    
    when((userServiceMock.addUserTags(exampleUser,sampleTags))).thenReturn(exampleUser);

    System.out.println(exampleUser);
    
    assertEquals(exampleUser, userControllerImpl.addUserTags(exampleUser,index,sampleTags));
  }
  
  @Test
  public void testAddingUser() {

    int index = 1;
    User exampleUser = new User();
    exampleUser.setId(index);
    exampleUser.setUsername("blah");
    exampleUser.setPassword("dsafjawjf");
    
    when((userServiceMock.create(exampleUser))).thenReturn(exampleUser);
    System.out.println(exampleUser);
    
    assertEquals(exampleUser, userControllerImpl.createUser(exampleUser));
  }
  
  @Test
  public void testDeletingUser() {
    User exampleUser = new User();
    
    
    int index = 1;
    exampleUser.setId(index);
    exampleUser.setUsername("retweet");
    exampleUser.setPassword("weguiawej");
    
    User newUser = exampleUser;
    
    userServiceMock.create(newUser);
    when((userServiceMock.findById(newUser.getId()))).thenReturn(newUser);
    userServiceMock.delete(newUser.getId());
    
    when((userServiceMock.findById(newUser.getId()))).thenReturn(null);
    assertEquals(null, userControllerImpl.findById(exampleUser.getId()));
  }
  
  @Test(expected = UserNotFoundException.class)
  public void testCreatingOrUpdateUser_Create() {
    
    // updating
    int index = 1; // the last object in the database so far.
    User exampleUser = new User();
    
    exampleUser.setId(index);
    exampleUser.setUsername("retweet");
    exampleUser.setPassword("weguiawej");
    exampleUser.setExpert(false);
    
    when((userServiceMock.createOrUpdate(exampleUser))).thenThrow(UserNotFoundException.class);
    
    when((userServiceMock.createOrUpdate(exampleUser))).thenReturn(exampleUser);
    assertEquals(exampleUser, userControllerImpl.createUser(exampleUser));
    
  }
  
  @Test(expected = UserConflictException.class)
  public void testCreatingOrUpdatingUser_Update() {
 // updating
    int index = 1; // the last object in the database so far.
    User exampleUser = new User();
    
    exampleUser.setId(index);
    exampleUser.setUsername("retweet");
    exampleUser.setPassword("weguiawej");
    exampleUser.setExpert(false);
     
    when((userServiceMock.createOrUpdate(exampleUser))).thenReturn(exampleUser);
    assertEquals(exampleUser, userServiceMock.createOrUpdate(exampleUser));
    
    System.out.println(exampleUser);
    User anotherExampleUser = new User();
    
    anotherExampleUser.setId(index);
    anotherExampleUser.setUsername("retweet");
    anotherExampleUser.setPassword("weguiawej");
    anotherExampleUser.setExpert(false);
    
    when((userServiceMock.createOrUpdate(anotherExampleUser))).thenThrow(UserConflictException.class);
    assertEquals(anotherExampleUser, userServiceMock.createOrUpdate(anotherExampleUser));
    System.out.println(anotherExampleUser);
    
    
    when((userServiceMock.findById(index))).thenReturn(exampleUser);
    //creating
    exampleUser.setId(index);
    exampleUser.setUsername("retweqwgwe");
    exampleUser.setPassword("weguiawgwgwj");
    exampleUser.setExpert(false);
    
    when((userServiceMock.createOrUpdate(exampleUser))).thenReturn(exampleUser);
    System.out.println(exampleUser);
    
    assertEquals(exampleUser, userControllerImpl.createUser(exampleUser));
    
  }
  
  /*private Set<Tag> looper(Tag[] loop) {
    for (int a = 0; a < loop.length; ++a) {
      loop[a] = new Tag();
    }
    return loop;
  }*/
}
