package com.recommend.app;

import org.springframework.data.annotation.Id;

public class Links {

  @Id
  public String id;

  public int movieid;
  public String link;

  public Links() {}

  public Links(int movieid, String link) {
    this.movieid = movieid;
    this.link = link;
  }

  @Override
  public String toString() {
    return String.format(
        "Link[id=%s, movieid='%d', link='%s']",
        id, movieid, link);
  }
}
