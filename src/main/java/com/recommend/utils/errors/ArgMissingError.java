package com.recommend.utils.errors;

import com.recommend.app.Arguments;

public class ArgMissingError extends IllegalArgumentException {

  String missingArg;
  String message;

  public ArgMissingError(String missingArg) {
      this.missingArg = missingArg;
      errMessage();
  }

  public void errMessage() {
    System.out.println("[ERROR : ArgMissingError]");
    this.message =
          "[ERROR : ArgMissingError] The argument '"+missingArg+"' is missing.";
  }

  public String getMessage() {
    return this.message;
  }
}
