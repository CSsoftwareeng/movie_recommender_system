package com.recommend.app;

import com.recommend.utils.errors.UserNotExistError;
import org.junit.Assert;
import org.junit.Test;

public class UserListTest {

  @Test(expected = UserNotExistError.class)
  public void testUserNotExistError() {
    UserList userlist = new UserList();
    userlist.searchMatchedUser(21);
  }

  @Test
  public void testSearchMatchedUser() {
    UserList userlist = new UserList();
    userlist.searchMatchedUser(17);
    Assert.assertEquals(userlist.matchedUsers.size(), 502);
    Assert.assertEquals(userlist.mostSimUsers.size(), 0);
    Assert.assertEquals(userlist.lessSimUsers.size(), 0);
    Assert.assertEquals(userlist.notSimUsers.size(), 0);
  }

  @Test
  public void testSearchSimilarUser() {
    UserList userlist = new UserList();
    userlist.searchSimilarUser("F", "25", "4");
    Assert.assertEquals(userlist.matchedUsers.size(), 59);
    Assert.assertEquals(userlist.mostSimUsers.size(), 815);
    Assert.assertEquals(userlist.lessSimUsers.size(), 2757);
    Assert.assertEquals(userlist.notSimUsers.size(), 2409);

    userlist = new UserList();
    userlist.searchSimilarUser("", "", "");
  }

  @Test
  public void testIsMatched() {
    UserList userlist = new UserList();
    userlist.searchSimilarUser("F", "25", "4");
    Assert.assertTrue(userlist.isMatched(219));
    Assert.assertFalse(userlist.isMatched(11));
  }

  @Test
  public void testIsSimilar() {
    UserList userlist = new UserList();
    userlist.searchSimilarUser("F", "25", "4");
    Assert.assertTrue(userlist.isSimilar(11));
    Assert.assertFalse(userlist.isSimilar(219));
  }

  @Test
  public void testIsLessSimilar() {
    UserList userlist = new UserList();
    userlist.searchSimilarUser("F", "25", "4");
    Assert.assertTrue(userlist.isLessSimilar(1));
    Assert.assertFalse(userlist.isLessSimilar(2));
  }

  @Test
  public void testIsNotSimilar() {
    UserList userlist = new UserList();
    userlist.searchSimilarUser("F", "25", "4");
    Assert.assertTrue(userlist.isNotSimilar(2));
    Assert.assertFalse(userlist.isNotSimilar(1));
  }
}
