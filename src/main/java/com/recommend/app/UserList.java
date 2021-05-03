package com.recommend.app;

import com.recommend.utils.errors.UserNotExistError;
import java.io.*;
import java.util.*;

public class UserList {

  TreeSet<Integer> matchedUsers = new TreeSet<Integer>();
  TreeSet<Integer> mostSimUsers = new TreeSet<Integer>();
  TreeSet<Integer> lessSimUsers = new TreeSet<Integer>();
  TreeSet<Integer> notSimUsers = new TreeSet<Integer>();

  void searchMatchedUser(int occupation) throws UserNotExistError {
    try {
      File usersFile = new File("./data/users.dat");
      FileReader reader = new FileReader(usersFile);
      BufferedReader buffer = new BufferedReader(reader);
      String line;
      while ((line = buffer.readLine()) != null) {
        String[] user = line.split("::");
        if (Integer.parseInt(user[3]) == occupation) {
          matchedUsers.add(Integer.parseInt(user[0]));
        }
      }
      if (matchedUsers.isEmpty()) throw new UserNotExistError();
    } catch (IOException e) {}
  }

  void searchSimilarUser(String gender, String age, String occupation) {
    boolean genderEmpty = gender.isEmpty();
    boolean ageEmpty = age.isEmpty();
    boolean occupationEmpty = occupation.isEmpty();

    try {
      File usersFile = new File("./data/users.dat");
      FileReader reader = new FileReader(usersFile);
      BufferedReader buffer = new BufferedReader(reader);
      String line;
      while ((line = buffer.readLine()) != null) {
        String[] user = line.split("::");
        int numMatched =
          (genderEmpty || user[1].equals(gender) ? 1 : 0) +
          (ageEmpty || user[2].equals(age) ? 1 : 0) +
          (occupationEmpty || user[3].equals(occupation) ? 1 : 0);
        switch (numMatched) {
          case 1:
            lessSimUsers.add(Integer.parseInt(user[0]));
            continue;
          case 2:
            mostSimUsers.add(Integer.parseInt(user[0]));
            continue;
          case 3:
            matchedUsers.add(Integer.parseInt(user[0]));
            continue;
          default:
            notSimUsers.add(Integer.parseInt(user[0]));
            continue;
        }
      }
    } catch (IOException e) {}
  }

  public boolean isMatched(int userid) {
    return matchedUsers.contains(userid);
  }

  public boolean isSimilar(int userid) {
    return mostSimUsers.contains(userid);
  }

  public boolean isLessSimilar(int userid) {
    return lessSimUsers.contains(userid);
  }

  public boolean isNotSimilar(int userid) {
    return notSimUsers.contains(userid);
  }
}
