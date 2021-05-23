package com.recommend.app;

import java.util.*;
import org.junit.Assert;
import org.junit.Test;

public class RatingCalculatorTest {

  @Test
  public void testrankUserBasedRating() {
      List<String> genres = new ArrayList<String>();
      genres.add("Romance");

      MovieList movielist = new MovieList(genres);
      UserList userlist = new UserList();
      userlist.searchSimilarUser("F", "30", "artist");

      RatingCalculator rating = new RatingCalculator(movielist, userlist);
      rating.rankUserBasedRating(10);
      rating.calcResult();
      Assert.assertEquals(10, rating.numMoviesResult());
      Assert.assertEquals(10, rating.moviesResult.size());

  }

  @Test
  public void testrankGenreBasedRating() {
      String title = "Toy Story (1995)";

      MovieList movielist = new MovieList();
      UserList userlist = new UserList();

      movielist.registerFavoriteMovie(title);
      userlist.searchFavoriteUsers(title);

      RatingCalculator rating = new RatingCalculator(movielist, userlist);
      rating.rankGenreBasedRating(20, true);
      rating.calcResult();
      Assert.assertEquals(20, rating.numMoviesResult());
      Assert.assertEquals(20,rating.moviesResult.size());
  }
}
