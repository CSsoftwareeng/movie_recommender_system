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

    int MovieID = Tool.getMovieID(title);
    
    List<Review> movieReviews = reviewRepository.findByMovieidOrderByUseridAsc(MovieID);
    List<Integer> users = new ArrayList<>();
    for (Review movieReview : movieReviews) {
      users.add(movieReview.userid);
    }
    List<Double> averageRatings = reviewRepository.findAverageRatingByUserid(users);

    int size = users.size();
    for ( int i = 0; i < size; i++) {
      if ( movieReviews.get(i).rating >= averageRatings.get(i) )
        favoriteUsers.add(users.get(i));
    }
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
