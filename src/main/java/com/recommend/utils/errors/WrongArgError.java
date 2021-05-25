package com.recommend.utils.errors;

import com.recommend.app.Arguments;

public class WrongArgError extends IllegalArgumentException {

  String missingArg;
  String message;

  public WrongArgError(String type) {
    errMessage(type);
  }

  public void errMessage(String type) {
    System.out.println("[ERROR : WrongArgError]");
    if (type == "user") this.message =
      "[ERROR : WrongArgError] There is unknown argument. [Valid arguments : gender, age, occupation, genres(optional)]";
    else if(type == "movie")
    this.message =
      "[ERROR : WrongArgError] There is unknown argument. [Valid arguments : title, limit(optional)]";
    else this.message = "[ERROR : WrongArgError] limit size should be greater than 0.";
  }

  public String getMessage() {
    return this.message;
  }
}
