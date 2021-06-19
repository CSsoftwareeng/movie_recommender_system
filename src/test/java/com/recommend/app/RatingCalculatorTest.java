package com.recommend.app;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingCalculatorTest {
  @Autowired
  UserList userlist;
  @Autowired
  MovieList movielist;
  @Autowired
  RatingCalculator rating;

  @Test
  public void testrankUserBasedRating() {
    List<String> genres = new ArrayList<String>();
    genres.add("Romance");

    movielist.searchID(genres);
    userlist.searchSimilarUser("F", "30", "2");

    rating.setLists(movielist, userlist);
    rating.rankUserBasedRating(10);
    rating.calcResult();
    Assert.assertEquals(10, rating.numMoviesResult());
    Assert.assertEquals(10, rating.moviesResult.size());
  }

  @Test
  public void testrankGenreBasedRating() {
    String title = "Toy Story (1995)";

    movielist.registerFavoriteMovie(title);
    userlist.searchFavoriteUsers(movielist.favoriteMovieID);

    rating.setLists(movielist, userlist);
    rating.rankGenreBasedRating(20, true);
    rating.calcResult();
    Assert.assertEquals(20, rating.numMoviesResult());
    Assert.assertEquals(20, rating.moviesResult.size());
  }
}
