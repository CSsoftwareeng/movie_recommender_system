package com.recommend.utils.errors;

public class ArgNotExistError extends IllegalArgumentException{
    public String user_input;
    public String processed_input;
    public boolean isGenre;

    public ArgNotExistError(String arg, String raw, boolean isGenre) {
        user_input = raw;
        processed_input = arg;
        this.isGenre = isGenre;
    }
    public String getArgType() {
        return isGenre ? "genre" : "occupation";
    }
}
