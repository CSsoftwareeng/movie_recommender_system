package com.recommend.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.recommend.utils.errors.MovieNotExistError;

public class MovieList {
    TreeSet<Integer> movies = new TreeSet<Integer>();
    List<String> movieName = new ArrayList<String>();

    void searchID(List<String> genres) throws MovieNotExistError {
        int genres_num = genres.size();
        try {
            File moviefile = new File("./data/movies.dat");
            FileReader fileReader = new FileReader(moviefile);
            BufferedReader bufReader = new BufferedReader(fileReader);
            String data = "";
            while ((data = bufReader.readLine()) != null) {
                String[] temp = data.split("::");
                if(genres.isEmpty() || genres.contains("")){
                    movieID.add(Integer.parseInt(temp[0]));
                }
                else{
                    for (int i = 0; i < genres_num; i++) {
                        if (temp[2].contains(genres.get(i))) {
                            movieID.add(Integer.parseInt(temp[0]));
                            break;
                        }
                    }
                }
            }
            if (!movies.isEmpty()) {
                return;
            }
            throw new MovieNotExistError(genres);
        } catch (IOException e) {}
    }

    public boolean findID(int MovieID) {
        return movies.contains(MovieID);
    }

    public TreeSet<Integer> getMoviesID() {
        return movies;
    }

    public void searchName(List<Integer> ID) throws IOException {
        try {
            File moviefile = new File("./data/movies.dat");
            FileReader fileReader = new FileReader(moviefile);
            BufferedReader bufReader = new BufferedReader(fileReader);
            String data = "";
            while ((data = bufReader.readLine()) != null) {
                String[] temp = data.split("::");
                if(ID.contains(Integer.parseInt(temp[0]))) {
                    movieName.add(temp[1]);
                }
                if(movieName.size() == 10) {
                    break;
                }
            }
        }catch (IOException e) {}
    }

    public List<String> getMoviesName() {
        return movieName;
    }
}
