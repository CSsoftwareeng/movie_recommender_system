package com.recommend.utils.errors;

public class ArgCntError extends IllegalArgumentException{
    public String[] user_input;
    public int number;
    public ArgCntError(String[] args, int argc) {
        user_input = args;
        number = argc;
    }
}
