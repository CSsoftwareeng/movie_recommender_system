package com.recommend.utils.errors;
import java.util.ArrayList;

public class MovieNotExistError extends Exception{

    public ArrayList<String> selected_genres;

    public MovieNotExistError(ArrayList<String> genres) {
        selected_genres = genres;
    }

    public void errorMessage() {
        System.out.println("Can't find a movie that belong to the selected genres: " + this.selected_genres);
        System.exit(0);
    }
}