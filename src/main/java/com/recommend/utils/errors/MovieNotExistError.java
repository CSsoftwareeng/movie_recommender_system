package com.recommend.utils.errors;
import java.util.List;

public class MovieNotExistError extends Exception{

    public List<String> selected_genres;

    public MovieNotExistError(List<String> genres) {
        selected_genres = genres;
    }

    public void errorMessage() {
        System.out.println("Can't find a movie that belong to the selected genres: " + this.selected_genres);
        System.out.println("Program was forced to exit.");
        System.exit(0);
    }
}
