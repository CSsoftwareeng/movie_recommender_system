package com.recommend.app;

import com.recommend.utils.errors.MovieNotExistError;
import java.util.*;
import org.junit.Assert;
import org.junit.Test;

public class ToolTest {

  @Test(expected = MovieNotExistError.class)
  public void testGetMovieGenreNotExistMovie() {
    Tool.getMovieGenre("NOT_EXIST_MOVIE_TITLE");
  }

  @Test
  public void testGetMovieGenre() {
    List<String> Genres = new ArrayList<String>();
    Genres.add("Animation");
    Genres.add("Children's");
    Genres.add("Comedy");
    Assert.assertEquals(Tool.getMovieGenre("Toy Story (1995)"), Genres);
  }

  @Test(expected = MovieNotExistError.class)
  public void testGetMovieIdNotExistMovie() {
    Tool.getMovieID("NOT_EXIST_MOVIE_TITLE");
  }

  @Test
  public void testgetMovieID() {
    Assert.assertEquals(Tool.getMovieID("Toy Story (1995)"), 1);
    Assert.assertEquals(Tool.getMovieID("American President, The (1995)"), 11);
    Assert.assertEquals(Tool.getMovieID("Instinct (1999)"), 2676);
  }
}
