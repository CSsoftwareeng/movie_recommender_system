package com.recommend.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import com.recommend.utils.errors.MovieNotExistError;

public class MovieList {
    TreeSet<Integer> movies = new TreeSet<Integer>();

    public void search(ArrayList<String> genres)throws IOException, MovieNotExistError {
        try {
            int genres_num = genres.size();
            File moviefile = new File(".data/movies.dat");
            FileReader fileReader = new FileReader(moviefile);
            BufferedReader bufReader = new BufferedReader(fileReader);
            String data = "";
            while ((data = bufReader.readLine()) != null) {
                String[] temp = data.split("::");
                for (int i = 0; i < genres_num; i++) {
                    if (!temp[2].contains(genres.get(i))) {
                        break;
                    }
                    if (i == genres_num -1) {
                        movies.add(Integer.parseInt(temp[0]));
                    }
                }
            }
            if(movies.isEmpty())
                throw new MovieNotExistError(genres);
        }catch (MovieNotExistError e) {
            e.errorMessage();
        }
    }

    public boolean find(int MovieID) {
        return movies.contains(MovieID);
    }

    public TreeSet<Integer> getMovies() {
        return movies;
    }
}
