package com.recommend.app;

import com.recommend.utils.errors.MovieNotExistError;
import java.util.*;
import org.junit.Assert;
import org.junit.Test;

public class ToolTest {

  @Test(expected = MovieNotExistError.class)
  public void testgetMovieGenre_exception() {
      Tool.getMovieGenre("NOT EXIST MOVIE TITLE");
  }

  @Test
  public void testgetMovieGenre() {
      List<String> Genres = new ArrayList<String>();
      Genres.add("Animation");
      Genres.add("Children's");
      Genres.add("Comedy");
      Assert.assertEquals(Tool.getMovieGenre("Toy Story (1995)"), Genres);
  }

  @Test(expected = MovieNotExistError.class)
  public void testgetMovieID_exception() {
      Tool.getMovieID("NOT EXIST MOVIE TITLE");
  }

  @Test
  public void testgetMovieID() {
      Assert.assertEquals(Tool.getMovieID("Toy Story (1995)"), 1);
  }
}
