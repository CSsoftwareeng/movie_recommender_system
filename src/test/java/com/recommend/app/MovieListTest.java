package com.recommend.app;

import com.recommend.utils.errors.MovieNotExistError;
import java.util.*;
import org.junit.Assert;
import org.junit.Test;

public class MovieListTest {

  List<String> genres = new ArrayList<String>();
  List<Integer> ids = new ArrayList<Integer>();
  Integer[] arrayid = new Integer[] { 34, 54, 66, 57, 1, 30, 888, 97, 14, 35 };
  String[] arrayname = new String[] {
    "Babe (1995)",
    "Big Green, The (1995)",
    "Lawnmower Man 2: Beyond Cyberspace (1996)",
    "Home for the Holidays (1995)",
    "Toy Story (1995)",
    "Shanghai Triad (Yao a yao yao dao waipo qiao) (1995)",
    "Land Before Time III: The Time of the Great Giving (1995)",
    "Hate (Haine, La) (1995)",
    "Nixon (1995)",
    "Carrington (1995)",
  };

  @Test(expected = MovieNotExistError.class)
  public void testMovieNotExistError() {
    genres.add("ABCDE");
    MovieList movie = new MovieList(genres);
  }

  @Test
  public void testGenreNotSpecifiedSearchID() {
    MovieList movie = new MovieList(genres);
    Assert.assertEquals(
      "testGenreNotSpecifiedSearchID",
      3883,
      movie.getMoviesID().size()
    );
  }

  @Test
  public void testEmptySearchID() {
    genres.add("");
    MovieList movie = new MovieList(genres);
    Assert.assertEquals("testEmptySearchID", 3883, movie.getMoviesID().size());
  }

  @Test
  public void testSearchID() {
    genres.add("Action");
    genres.add("Drama");
    MovieList movie = new MovieList(genres);
    Assert.assertEquals("testSearchID", 2006, movie.getMoviesID().size());
  }

  @Test
  public void testFindID() {
    genres.add("Action");
    genres.add("Drama");
    MovieList movie = new MovieList(genres);
    Assert.assertEquals("testFindID1", true, movie.findID(17));
    Assert.assertEquals("testFindID2", true, movie.findID(20));
    Assert.assertEquals("testFindID3", false, movie.findID(176));
  }

  @Test
  public void testEmptySearchName() {
    List<String> expected = new ArrayList<String>();
    MovieList movie = new MovieList(genres);
    movie.searchName(ids);
    Assert.assertEquals("testEmptySearchName", expected, movie.getMoviesName());
  }

  @Test
  public void testSearchName() {
    List<String> expected = new ArrayList<String>();
    expected.addAll(Arrays.asList(arrayname));
    ids.addAll(Arrays.asList(arrayid));
    MovieList movie = new MovieList(genres);
    movie.searchName(ids);
    Assert.assertEquals("testSearchNmae", expected, movie.getMoviesName());
  }

  @Test
  public void testBufferSearch() {
    List<String> expected = new ArrayList<String>();
    expected.addAll(Arrays.asList(arrayname));
    ids.addAll(Arrays.asList(arrayid));
    ids.add(3952);
    expected.add("Contender, The (2000)");
    MovieList movie = new MovieList(genres);
    movie.searchName(ids);
    Assert.assertEquals("testBufferSearch", expected, movie.getMoviesName());
  }

  @Test
  public void testSearchSimilarID() {
    MovieList movie = new MovieList();
    List<String> genres = new ArrayList<String>();
    genres.add("Romance");
    genres.add("Comedy");

    movie.searchSimilarID(genres);
    Assert.assertEquals(2, movie.similarMovies.size());
  }

  @Test
  public void testRegisterFavoriteMovie() {
    MovieList movie = new MovieList();
    movie.registerFavoriteMovie("Toy Story (1995)");

    Assert.assertEquals(3, movie.similarMovies.size());
    Assert.assertEquals(3, movie.countMathcedGenres(1));
  }
}
