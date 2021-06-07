package com.recommend.app;

import java.util.*;
import org.springframework.data.annotation.Id;

public class Movie {

  @Id
  public String id;

  public int movieid;
  public String title;
  public String genres;
  public String imdb;
  public String poster;

  public Movie() {}

  public Movie(int movieid, String title, String genres) {
    this.movieid = movieid;
    this.title = title;
    this.genres = genres;
  }

  public Movie(String title, String genres, String imdb, String poster) {
    this.title = title;
    this.genres = genres;
    this.imdb = imdb;
    this.poster = poster;

  }

  @Override
  public String toString() {
    return String.format(
        "Movie[id=%s, movieid='%d', title='%s', genre='%s']",
        id, movieid, title, genres);
  }

  public String getMovieid() {
    return this.title;
  }

  public String getTitle() {
    return this.title;
  }

  public String getGenres() {
    return this.genres;
  }

  public String getImdb() {
    return this.imdb;
  }

  public String getPoster() {
    return this.poster;
  }
}
