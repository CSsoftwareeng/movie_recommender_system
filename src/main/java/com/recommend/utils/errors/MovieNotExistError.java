package com.recommend.utils.errors;
import java.util.List;

public class MovieNotExistError extends IllegalArgumentException{

    public List<String> selected_genres;

    public MovieNotExistError(List<String> genres) {
        selected_genres = genres;
        errorMessage();
    }

    public void errorMessage() {
        System.out.println("Can't find a movie that belong to the selected genres: " + this.selected_genres);
        System.out.println("-------------Program was forced to exit.-------------\n");
        System.exit(0);
    }
}
