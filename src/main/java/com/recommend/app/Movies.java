package com.recommend.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Movies {
    HashMap<Integer, String> titles = new HashMap<>();
    HashMap<Integer, String> genres = new HashMap<>();
    List moviesResult = new ArrayList<Movie>();

    public void searchAllMovies() {
        try {
            File moviefile = new File("./data/movies.dat");
            FileReader fileReader = new FileReader(moviefile);
            BufferedReader bufReader = new BufferedReader(fileReader);
            String data = "";
            while ((data = bufReader.readLine()) != null) {
                String[] temp = data.split("::");
                this.titles.put(Integer.parseInt(temp[0]), temp[1]);
                this.genres.put(Integer.parseInt(temp[0]), temp[2]);
            }
        } catch (IOException e) {}

        try {
            File linkfile = new File("./data/links.dat");
            FileReader fileReader = new FileReader(linkfile);
            BufferedReader bufReader = new BufferedReader(fileReader);
            String data = "";
            while ((data = bufReader.readLine()) != null) {
                String[] temp = data.split("::");
                String movietitle = "";
                String moviegenre = "";
                String movielink = "";
                movietitle = this.titles.get(Integer.parseInt(temp[0]));
                moviegenre = this.genres.get(Integer.parseInt(temp[0]));
                movielink = temp[1];
                Movie movie = new Movie(
                        movietitle,
                        moviegenre,
                        "(http://www.imdb.com/title/tt" + movielink + ")"
                );
                this.moviesResult.add(movie);
            }
        } catch (IOException e) {}
    }

    public List getMoviesResult() {
        return this.moviesResult;
    }
}
