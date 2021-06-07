package com.recommend.app;

import java.util.*;

public class Movie {

  String title;
  String genres;
  String imdb;
  String poster;

  public Movie(String title, String genres, String imdb, String poster) {
    this.title = title;
    this.genres = genres;
    this.imdb = imdb;
    this.poster = poster;

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
