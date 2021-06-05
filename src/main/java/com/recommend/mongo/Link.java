package com.recommend.app;

import org.springframework.data.annotation.Id;

public class Link {

  @Id
  public String id;

  public int movieid;
  public int link;

  public Link() {}

  public Link(int movieid, int link) {
    this.movieid = movieid;
    this.link = link;
  }

  @Override
  public String toString() {
    return String.format(
        "Link[id=%s, movieid='%d', link='%d']",
        id, movieid, link);
  }
}
