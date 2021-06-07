package com.recommend.app;

import org.springframework.data.annotation.Id;

public class Movie {

  @Id
  public String id;

  public int movieid;
  public String title;
  public String genres;

  public Movie() {}

  public Movie(int movieid, String title, String genres) {
    this.movieid = movieid;
    this.title = title;
    this.genres = genres;
  }

  @Override
  public String toString() {
    return String.format(
        "Movie[id=%s, movieid='%d', title='%s', genres='%s']",
        id, movieid, title, genres);
  }
}
