package com.recommend.app;

import com.recommend.utils.errors.MovieNotExistError;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MovieList {

  TreeSet<Integer> movies = new TreeSet<Integer>();
  List<String> movieName = new ArrayList<String>();
  List<String> movieGenres = new ArrayList<String>();
  List<String> favoriteGenre = new ArrayList<String>();
  Integer favoriteMovieID;

  public MovieList(List<String> genres) {
    searchID(genres);
  }

  void searchID(List<String> genres) throws MovieNotExistError {
    int genres_num = genres.size();
    try {
      File moviefile = new File("./data/movies.dat");
      FileReader fileReader = new FileReader(moviefile);
      BufferedReader bufReader = new BufferedReader(fileReader);
      String data = "";
      while ((data = bufReader.readLine()) != null) {
        String[] temp = data.split("::");
        if (genres.isEmpty() || genres.contains("")) {
          movies.add(Integer.parseInt(temp[0]));
        } else {
          for (int i = 0; i < genres_num; i++) {
            if (temp[2].contains(genres.get(i))) {
              movies.add(Integer.parseInt(temp[0]));
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

  public void searchName(List<Integer> ID) {
    try {
      Integer[] ids = new Integer[ID.size()];
      String[] names = new String[ID.size()];
      String[] genres = new String[ID.size()];
      File moviefile = new File("./data/movies.dat");
      FileReader fileReader = new FileReader(moviefile);
      BufferedReader bufReader = new BufferedReader(fileReader);
      String data = "";
      for (
        int i = 0;
        ((data = bufReader.readLine()) != null) && i < ID.size();
      ) {
        String[] temp = data.split("::");
        if (ID.contains(Integer.parseInt(temp[0]))) {
          ids[i] = Integer.parseInt(temp[0]);
          names[i] = temp[1];
          genres[i] = temp[2];
          i++;
        }
      }
      if (ID.size() != 0) {
        for (int j = 0; j < ID.size(); j++) {
          int index = Arrays.asList(ids).indexOf(ID.get(j));
          movieName.add(names[index]);
          movieGenres.add(genres[index]);
        }
      }
    } catch (IOException e) {}
  }

  public List<String> getMoviesName() {
    return movieName;
  }
  public List<String> getMovieGenres() {return movieGenres;} //

  public List<Integer> searchSimilarID(int match, List<String> genres) throws MovieNotExistError {
    List<Integer> movies = new ArrayList<Integer>();
    int genres_num = genres.size();
    try {
      File moviefile = new File("./data/movies.dat");
      FileReader fileReader = new FileReader(moviefile);
      BufferedReader bufReader = new BufferedReader(fileReader);
      String data = "";
      while ((data = bufReader.readLine()) != null) {
        int count = 0;
        String[] temp = data.split("::");
        for (int i = 0; i < genres_num; i++) {
          if (temp[2].contains(genres.get(i))) {
            count++;
          }
        }
        if (count == match) {
          movies.add(Integer.parseInt(temp[0]));
        }
      }
      if (movies.isEmpty()) {
        throw new MovieNotExistError(genres);
      }
    } catch (IOException e) {} catch (MovieNotExistError e) {}

    return movies;
  }

  public void searchFavoriteMovie(String name) {
    boolean isFind = false;
    String raw_name = null;
    raw_name = name.trim().toLowerCase().replaceAll("\\p{Z}", "").replaceAll("\\p{Punct}", "");
    try {
      File moviefile = new File("./data/movies.dat");
      FileReader fileReader = new FileReader(moviefile);
      BufferedReader bufReader = new BufferedReader(fileReader);
      String data = "";
      while ((data = bufReader.readLine()) != null) {
        String[] temp = data.split("::");
        String raw_name2 = null;
        String raw_name3 = null;
        raw_name2 = temp[1].trim().toLowerCase().replaceAll("\\p{Z}", "").replaceAll("\\p{Punct}", "");
        raw_name3 = raw_name2.substring(0, (raw_name2.length()-4));
        if (raw_name.equals(raw_name2) || raw_name.equals(raw_name3)) {
          isFind = true;
          favoriteMovieID = Integer.parseInt(temp[0]);
          String[] temp_genres = temp[2].split("\\|");
          for (int i = 0; i< temp_genres.length; i++) {
            favoriteGenre.add(temp_genres[i]);
          }
          break;
        }
      }
      if (isFind) {
        return;
      }
      throw new MovieNotExistError(name);
    } catch (IOException e) {} catch (MovieNotExistError e) {}
  }
}
