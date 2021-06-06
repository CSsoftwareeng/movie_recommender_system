package com.recommend.app;

import org.springframework.data.annotation.Id;

public class Ratings {

  @Id
  public String id;

  public int userid;
  public int movieid;
  public int rating;
  public int date;

  public Ratings() {}

  public Ratings(int userid, int movieid, int rating, int date) {
    this.userid = userid;
    this.movieid = movieid;
    this.rating = rating;
    this.date = date;
  }

  @Override
  public String toString() {
    return String.format(
        "Rating[id=%s, userid='%d', movieid='%d', rating='%d', date='%d']",
        userid,movieid,rating,date);
  }
}
