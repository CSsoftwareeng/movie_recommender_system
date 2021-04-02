package com.recommend.utils.errors;

public class UserNotExistError extends IllegalArgumentException{

    public UserNotExistError() {
        super();
        errMessage();
    }

    public void errMessage() {
        System.out.println("Can't find any user whose occupation matchs");
        System.out.println("-------------Program was forced to exit.-------------\n");
        System.exit(0);
    }
}
