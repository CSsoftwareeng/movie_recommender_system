package com.recommend.app;

import java.util.*;
import org.junit.Assert;
import org.junit.Test;

public class MovieTest {

  @Test
  public void testConstructor() {
    Movie movie = new Movie("title", "genres", "imdb");
    Assert.assertEquals("title", movie.getTitle());
    Assert.assertEquals("genres", movie.getGenres());
    Assert.assertEquals("imdb", movie.getImdb());
  }
}
