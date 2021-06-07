package com.recommend.app;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import com.recommend.utils.errors.UserNotExistError;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;
import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserListTest {
  @Autowired
  private UserList userlist;

  @Test(expected = UserNotExistError.class)
  public void testUserNotExistError() {
    userlist.searchMatchedUser(21);
  }

  @Test
  public void testSearchMatchedUser() {
    
    userlist.searchMatchedUser(17);
    Assert.assertEquals(userlist.matchedUsers.size(), 502);
    Assert.assertEquals(userlist.mostSimUsers.size(), 0);
    Assert.assertEquals(userlist.lessSimUsers.size(), 0);
    Assert.assertEquals(userlist.notSimUsers.size(), 0);
  }

  @Test
  public void testSearchSimilarUser() {
    userlist.searchSimilarUser("F", "25", "4");
    Assert.assertEquals(userlist.matchedUsers.size(), 59);
    Assert.assertEquals(userlist.mostSimUsers.size(), 815);
    Assert.assertEquals(userlist.lessSimUsers.size(), 2757);
    Assert.assertEquals(userlist.notSimUsers.size(), 2409);

    userlist.searchSimilarUser("", "", "");
  }

  @Test
  public void testIsMatched() {
    userlist.searchSimilarUser("F", "25", "4");
    Assert.assertTrue(userlist.isMatched(219));
    Assert.assertFalse(userlist.isMatched(11));
  }

  @Test
  public void testIsSimilar() {
    userlist.searchSimilarUser("F", "25", "4");
    Assert.assertTrue(userlist.isSimilar(11));
    Assert.assertFalse(userlist.isSimilar(219));
  }

  @Test
  public void testIsLessSimilar() {
    userlist.searchSimilarUser("F", "25", "4");
    Assert.assertTrue(userlist.isLessSimilar(1));
    Assert.assertFalse(userlist.isLessSimilar(2));
  }

  @Test
  public void testIsNotSimilar() {
    userlist.searchSimilarUser("F", "25", "4");
    Assert.assertTrue(userlist.isNotSimilar(2));
    Assert.assertFalse(userlist.isNotSimilar(1));
  }
}
