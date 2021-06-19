package com.recommend.app;

import org.springframework.data.annotation.Id;

public class Cache {

  @Id
  public String id;

  public String type;
  public String title;
  public String genres;
  public String imdb;
  public String poster;

  public Cache() {}

  public Cache(String type, String title, String genres, String imdb, String poster) {
    this.type = type;
    this.title = title;
    this.genres = genres;
    this.genres = imdb;
    this.genres = poster;
  }
}
