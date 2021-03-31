package com.recommend.utils.errors;

import java.util.Arrays;

public class MovieNotExistError extends Exception{

    public String[] selected_genres;

    public MovieNotExistError(String[] genres) {
        selected_genres = genres;
    }

    public void errorMessage() {
        System.out.println("Can't find a movie that belong to the selected genres: " + Arrays.toString(this.selected_genres));
        System.exit(0);
    }
}
