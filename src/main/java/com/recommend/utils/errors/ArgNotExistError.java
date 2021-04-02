package com.recommend.utils.errors;
import com.recommend.app.Arguments;

public class ArgNotExistError extends IllegalArgumentException{
    String user_input;
    String processed_input;
    boolean isGenre;

    public ArgNotExistError(Arguments args, boolean isGenre) {
        this.isGenre = isGenre;
        user_input = isGenre ? args.getRawGenre() : args.getRawOccupation();
        processed_input = args.err_sender_str;
        errMessage();
    }
    public String getArgType(boolean isGenre) {
        return isGenre ? "genre" : "occupation";
    }
    public void errMessage() {
        if (this.isGenre) {
            System.out.println("[ERROR : ArgNotExistError]");
            System.out.println("  The movie genre [" + this.user_input + "] is invalid.");
            System.out.println("  Can't find [" + this.processed_input + "] in the list.");
            System.out.println("-------------Program was forced to exit.-------------\n");
            System.exit(0);
        }
        else {
            System.out.println("[ERROR : ArgNotExistError]");
            System.out.println("  Can't find [" + this.user_input + "] in the available occupation list.");
            System.out.println("-------------Program was forced to exit.-------------\n");
            System.exit(0);
        }
    }
}
