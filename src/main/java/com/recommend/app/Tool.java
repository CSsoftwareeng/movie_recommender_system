package com.recommend.app;

import com.recommend.utils.errors.MovieNotExistError;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Tool {

  public static List<String> getMovieGenre(String name)
    throws MovieNotExistError {
    List<String> Genres = new ArrayList<String>();
    boolean isFind = false;
    String raw_name = null;
    raw_name =
      name
        .trim()
        .toLowerCase()
        .replaceAll("\\p{Z}", "")
        .replaceAll("\\p{Punct}", "");
    try {
      File moviefile = new File("./data/movies.dat");
      FileReader fileReader = new FileReader(moviefile);
      BufferedReader bufReader = new BufferedReader(fileReader);
      String data = "";
      while ((data = bufReader.readLine()) != null) {
        String[] temp = data.split("::");
        String raw_name2 = null;
        String raw_name3 = null;
        raw_name2 =
          temp[1].trim()
            .toLowerCase()
            .replaceAll("\\p{Z}", "")
            .replaceAll("\\p{Punct}", "");
        raw_name3 = raw_name2.substring(0, (raw_name2.length() - 4));
        if (raw_name.equals(raw_name2) || raw_name.equals(raw_name3)) {
          isFind = true;
          String[] temp_genres = temp[2].split("\\|");
          Genres.addAll(Arrays.asList(temp_genres));
          break;
        }
      }
      if (!isFind) {
        throw new MovieNotExistError(name);
      }
    } catch (IOException e) {} catch (MovieNotExistError e) {}
    return Genres;
  }

  public static int getMovieID(String name) throws MovieNotExistError {
    int favoriteMovieID = 0;
    boolean isFind = false;
    String raw_name = null;
    raw_name =
      name
        .trim()
        .toLowerCase()
        .replaceAll("\\p{Z}", "")
        .replaceAll("\\p{Punct}", "");
    try {
      File moviefile = new File("./data/movies.dat");
      FileReader fileReader = new FileReader(moviefile);
      BufferedReader bufReader = new BufferedReader(fileReader);
      String data = "";
      while ((data = bufReader.readLine()) != null) {
        String[] temp = data.split("::");
        String raw_name2 = null;
        String raw_name3 = null;
        raw_name2 =
          temp[1].trim()
            .toLowerCase()
            .replaceAll("\\p{Z}", "")
            .replaceAll("\\p{Punct}", "");
        raw_name3 = raw_name2.substring(0, (raw_name2.length() - 4));
        if (raw_name.equals(raw_name2) || raw_name.equals(raw_name3)) {
          isFind = true;
          favoriteMovieID = Integer.parseInt(temp[0]);
          break;
        }
      }
      if (!isFind) {
        throw new MovieNotExistError(name);
      }
    } catch (IOException e) {} catch (MovieNotExistError e) {}
    return favoriteMovieID;
  }
}
