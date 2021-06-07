package com.recommend.app;

import java.util.*;
import org.junit.Assert;
import org.junit.Test;

public class MovieTest {

  @Test
  public void testConstructor() {
    Movies movie = new Movies("title", "genres", "imdb", "poster");
    Assert.assertEquals("title", movie.getTitle());
    Assert.assertEquals("genres", movie.getGenres());
    Assert.assertEquals("imdb", movie.getImdb());
  }
}
