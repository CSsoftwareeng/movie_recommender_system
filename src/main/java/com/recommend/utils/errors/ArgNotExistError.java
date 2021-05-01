package com.recommend.utils.errors;

import com.recommend.app.Arguments;

public class ArgNotExistError extends IllegalArgumentException {

  String user_input;
  String processed_input;
  int argType;

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
      case 4:
        user_input = args.getRawGenre();
        break;
    }
    processed_input = args.err_sender_str;
    errMessage();
  }

  public void errMessage() {
    switch (this.argType) {
      case 1:
        System.out.println("[ERROR : ArgNotExistError]");
        System.out.println("  The gender should be \"F\" or \"M\".");
        System.out.println("  [" + this.user_input + "] is invalid input.");
        System.out.println(
          "-------------Program was forced to exit.-------------\n"
        );
        break;
      case 2:
        System.out.println("[ERROR : ArgNotExistError]");
        System.out.println("  The age should be greater than 0.");
        System.out.println("  [" + this.user_input + "] is invalid input.");
        System.out.println(
          "-------------Program was forced to exit.-------------\n"
        );
        break;
      case 3:
        System.out.println("[ERROR : ArgNotExistError]");
        System.out.println(
          "  Can't find [" +
          this.user_input +
          "] in the available occupation list."
        );
        System.out.println(
          "-------------Program was forced to exit.-------------\n"
        );
        break;
      case 4:
        System.out.println("[ERROR : ArgNotExistError]");
        System.out.println(
          "  The movie genre [" + this.user_input + "] is invalid."
        );
        System.out.println(
          "  Can't find [" + this.processed_input + "] in the list."
        );
        System.out.println(
          "-------------Program was forced to exit.-------------\n"
        );
        break;
    }
  }
}
