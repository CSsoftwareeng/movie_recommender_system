package com.recommend.utils.errors;

import java.util.List;

public class MovieNotExistError extends IllegalArgumentException {

  public List<String> selected_genres;
  public String selected_names;
  String message = null;

  public MovieNotExistError(List<String> genres) {
    selected_genres = genres;
    errorMessage();
  }

  public MovieNotExistError(String names) {
    selected_names = names;
    errorMessage(selected_names);
  }

  public void errorMessage() {
    System.out.println("[ERROR : MovieNotExistError]");
    this.message =
      "[ERROR : MovieNotExistError] Can't find any movie that belong to at least one selected genres: " +
      this.selected_genres;
  }

  public void errorMessage(String name) {
    System.out.println("[ERROR : MovieNotExistError]");
    this.message =
      "[ERROR : MovieNotExistError] Can't find a movie titled: " +
      this.selected_names;
  }

  public String getMessage() {
    return this.message;
  }
}
