package com.recommend.app;
import org.springframework.beans.factory.annotation.Autowired;
import com.recommend.utils.errors.UserNotExistError;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.*;

@Component
public class UserList {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private ReviewRepository reviewRepository;

  TreeSet<Integer> matchedUsers = new TreeSet<Integer>();
  TreeSet<Integer> mostSimUsers = new TreeSet<Integer>();
  TreeSet<Integer> lessSimUsers = new TreeSet<Integer>();
  TreeSet<Integer> notSimUsers = new TreeSet<Integer>();
  TreeSet<Integer> favoriteUsers = new TreeSet<Integer>();

  void searchMatchedUser(int occupation) throws UserNotExistError {
    matchedUsers = new TreeSet<Integer>();
    mostSimUsers = new TreeSet<Integer>();
    lessSimUsers = new TreeSet<Integer>();
    notSimUsers = new TreeSet<Integer>();
    favoriteUsers = new TreeSet<Integer>();
    List<Integer> temp = userRepository.findUseridByOccupation(occupation);
    matchedUsers = new TreeSet<Integer>(temp);
    if (matchedUsers.isEmpty()) throw new UserNotExistError();
  }

  void searchSimilarUser(String gender, String age, String occupation) {
    boolean genderEmpty = gender.isEmpty();
    boolean ageEmpty = age.isEmpty();
    boolean occupationEmpty = occupation.isEmpty();

    matchedUsers = new TreeSet<Integer>();
    mostSimUsers = new TreeSet<Integer>();
    lessSimUsers = new TreeSet<Integer>();
    notSimUsers = new TreeSet<Integer>();
    favoriteUsers = new TreeSet<Integer>();
    // System.out.println("gender: " + gender + "age: " + age + "occupation :" + occupation);

    List<User> temp = userRepository.findAll();
    for (User user : temp) {
      int numMatched =
        (genderEmpty || user.gender.equals(gender) ? 1 : 0) +
        (ageEmpty || user.age == Integer.parseInt(age) ? 1 : 0) +
        (occupationEmpty || user.occupation == Integer.parseInt(occupation) ? 1 : 0);
      int userid = user.userid;
      switch (numMatched) {
        case 1:
          lessSimUsers.add(userid);
          continue;
        case 2:
          mostSimUsers.add(userid);
          continue;
        case 3:
          matchedUsers.add(userid);
          continue;
        default:
          notSimUsers.add(userid);
          continue;
      }
    }
  }

  void searchFavoriteUsers(String title) {
    matchedUsers = new TreeSet<Integer>();
    mostSimUsers = new TreeSet<Integer>();
    lessSimUsers = new TreeSet<Integer>();
    notSimUsers = new TreeSet<Integer>();
    favoriteUsers = new TreeSet<Integer>();

    HashMap<Integer, Integer> users = new HashMap<Integer, Integer>();
    HashMap<Integer, AvgRating> usersAvg = new HashMap<Integer, AvgRating>();
    int MovieID = Tool.getMovieID(title);
    try {
      File ratingFile = new File("./data/ratings.dat");
      FileReader reader = new FileReader(ratingFile);
      BufferedReader buffer = new BufferedReader(reader);
      String line;
      while ((line = buffer.readLine()) != null) {
        String[] rating = line.split("::");
        if (Integer.parseInt(rating[1]) == MovieID) {
          users.put(Integer.parseInt(rating[0]), Integer.parseInt(rating[2]));
        }
      }
    } catch (IOException e) {}

    try {
      File ratingFile = new File("./data/ratings.dat");
      FileReader reader = new FileReader(ratingFile);
      BufferedReader buffer = new BufferedReader(reader);
      String line;
      while ((line = buffer.readLine()) != null) {
        String[] rating = line.split("::");
        if (users.containsKey(Integer.parseInt(rating[0]))) {
          if (usersAvg.containsKey(Integer.parseInt(rating[0]))) {
            AvgRating temp = new AvgRating(
              (
                usersAvg.get(Integer.parseInt(rating[0])).getSum() +
                Integer.parseInt(rating[2])
              ),
              (usersAvg.get(Integer.parseInt(rating[0])).getCount() + 1)
            );
            temp.setAverage();
            usersAvg.replace(Integer.parseInt(rating[0]), temp);
          } else {
            AvgRating temp = new AvgRating(Integer.parseInt(rating[2]), 1);
            temp.setAverage();
            usersAvg.put(Integer.parseInt(rating[0]), temp);
          }
        }
      }

      for (Integer key : users.keySet()) {
        if (users.get(key) >= usersAvg.get(key).getAverage()) {
          favoriteUsers.add(key);
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

  public boolean isFavorite(int userid) {
    return favoriteUsers.contains(userid);
  }
}
