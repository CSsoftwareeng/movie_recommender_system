package com.recommend.utils.errors;

import java.util.Arrays;

public class ArgCntError extends IllegalArgumentException {

  int number;
  String message = null;

  public ArgCntError(int argc) {
    number = argc;
    errMessage();
  }

  public void errMessage() {
    System.out.println("[ERROR : ArgCntError]");
    this.message =
      "[ERROR : ArgCntError] " +
      this.number +
      " is invalid number of arguments.";
  }

  public String getMessage() {
    return this.message;
  }
}
