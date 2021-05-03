package com.recommend.utils.errors;

public class UserNotExistError extends IllegalArgumentException {

  public UserNotExistError() {
    super();
    errMessage();
  }

  public void errMessage() {
    System.out.println("[ERROR : UserNotExistError]");
    System.out.println("Can't find any corresponding user.");
    System.out.println(
      "-------------Program was forced to exit.-------------\n"
    );
  }
}
