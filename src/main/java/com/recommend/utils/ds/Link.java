package com.recommend.app;

import org.springframework.data.annotation.Id;

public class Link {

  @Id
  public String id;

  public int movieid;
  public String link;

  public Link() {}

  public Link(int movieid, String link) {
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
