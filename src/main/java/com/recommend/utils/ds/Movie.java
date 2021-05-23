package com.recommend.app;

import java.util.*;

public class Movie {

  String title;
  String genres;
  String imdb;

  public Movie(String title, String genres, String imdb) {
    this.title = title;
    this.genres = genres;
    this.imdb = imdb;
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
}
