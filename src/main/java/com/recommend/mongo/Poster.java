package com.recommend.app;

import org.springframework.data.annotation.Id;

public class Poster {

  @Id
  public String id;

  public int movieid;
  public String poster;

  public Poster() {}

  public Poster(int movieid, String poster) {
    this.movieid = movieid;
    this.poster = poster;
  }

  @Override
  public String toString() {
    return String.format(
        "Poster[id=%s, movieid='%d', poster='%s']",
        id, movieid, poster);
  }
}
