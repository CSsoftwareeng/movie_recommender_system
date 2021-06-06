package com.recommend.app;

import org.springframework.data.annotation.Id;

public class Movies {

  @Id
  public String id;

  public int movieid;
  public String title;
  public String genre;

  public Movies() {}

  public Movies(int movieid, String title, String genre) {
    this.movieid = movieid;
    this.title = title;
    this.genre = genre;
  }

  @Override
  public String toString() {
    return String.format(
        "Movie[id=%s, movieid='%d', title='%s', genre='%s']",
        id, movieid, title, genre);
  }
}
