package com.recommend.utils.errors;

import com.recommend.app.Arguments;

public class ArgNotExistError extends IllegalArgumentException {

  String user_input;
  String processed_input;
  int argType;
  String message;

  public ArgNotExistError(Arguments args, int argType) {
    this.argType = argType;
    switch (argType) {
      case 1:
        user_input = args.getGender();
        break;
      case 2:
        user_input = args.getAge();
        break;
      case 3:
        user_input = args.getRawOccupation();
        break;
      default:
        user_input = args.getRawGenre();
        break;
    }
    processed_input = args.err_sender_str;
    errMessage();
  }

  public void errMessage() {
    System.out.println("[ERROR : ArgNotExistError]");
    switch (this.argType) {
      case 1:
        this.message =
          "[ERROR : ArgNotExistError] The gender should be F or M. [" +
          this.user_input +
          "] is invalid input.";
        break;
      case 2:
        this.message =
          "[ERROR : ArgNotExistError] The age should be greater than 0." +
          this.user_input +
          "] is invalid input.";
        break;
      case 3:
        this.message =
          "[ERROR : ArgNotExistError] Can't find [" +
          this.user_input +
          "] in the available occupation list.";
        break;
      default:
        this.message =
          "[ERROR : ArgNotExistError] The movie genre [" +
          this.user_input +
          "] is invalid. Can't find [" +
          this.processed_input +
          "] in the list.";
        break;
    }
  }

  public String getMessage() {
    return this.message;
  }
}
