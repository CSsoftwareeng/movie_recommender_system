package com.recommend.app;

import java.io.*;
import java.util.*;

public class RatingCalculator {

  HashMap<Integer, Rating> map = new HashMap<>();
  LinkedHashMap<Integer, Rating> result = new LinkedHashMap<>();
  List<String> names;
  List<String> genres;
  List<Integer> ID;
  List moviesResult = new ArrayList<Movie>();
  MovieList movies;
  UserList users;

  public RatingCalculator(MovieList movieList, UserList userList) {
    this.movies = movieList;
    this.users = userList;
  }

  void rankUserBasedRating(int limit) {
    try {
      File usersFile = new File("./data/ratings.dat");
      FileReader reader = new FileReader(usersFile);
      BufferedReader buffer = new BufferedReader(reader);
      String line;
      int matched = 3;
      while ((line = buffer.readLine()) != null) {
        String[] rating = line.split("::");
        int user = Integer.parseInt(rating[0]);
        int movie = Integer.parseInt(rating[1]);
        if (users.isMatched(user)) {
          matched = 3;
        } else if (users.isSimilar(user)) {
          matched = 2;
        } else if (users.isLessSimilar(user)) {
          matched = 1;
        } else {
          matched = 0;
        }

        if (movies.findID(movie)) {
          if (map.containsKey(movie)) {
            if (map.get(movie).getMatch() == matched) {
              int tsum = map.get(movie).getSum() + Integer.parseInt(rating[2]);
              int tcount = map.get(movie).getCount() + 1;
              map.put(movie, new Rating(tsum, tcount, matched));
            } else if (map.get(movie).getMatch() < matched) {
              map.put(
                movie,
                new Rating(Integer.parseInt(rating[2]), 1, matched)
              );
            }
          } else {
            map.put(movie, new Rating(Integer.parseInt(rating[2]), 1, matched));
          }
        }
      }
    } catch (IOException e) {}

    List<Map.Entry<Integer, Rating>> entries = new LinkedList<>(map.entrySet());
    Collections.sort(
      entries,
      (o1, o2) -> o2.getValue().compareTo(o1.getValue())
    );

    for (Map.Entry<Integer, Rating> entry : entries) {
      if (result.size() < limit) result.put(
        entry.getKey(),
        entry.getValue()
      ); else break;
    }
  }

  void rankGenreBasedRating(int limit, boolean userfilter) {
    try {
      File usersFile = new File("./data/ratings.dat");
      FileReader reader = new FileReader(usersFile);
      BufferedReader buffer = new BufferedReader(reader);
      String line;

      while ((line = buffer.readLine()) != null) {
        String[] rating = line.split("::");
        int user = Integer.parseInt(rating[0]);
        int movie = Integer.parseInt(rating[1]);
        int match = movies.countMathcedGenres(movie);

        if (!userfilter || users.isFavorite(user)) {
          if (map.containsKey(movie)) {
            int tsum = map.get(movie).getSum() + Integer.parseInt(rating[2]);
            int tcount = map.get(movie).getCount() + 1;
            map.put(movie, new Rating(tsum, tcount, match));
          } else {
            map.put(movie, new Rating(Integer.parseInt(rating[2]), 1, match));
          }
        }
      }
      map.remove(movies.favoriteMovieID);
      if (!userfilter) {
        List<Integer> keys = new ArrayList<>(result.keySet());
        for (Integer key : keys) {
          map.remove(key);
        }
      }
    } catch (IOException e) {}

    List<Map.Entry<Integer, Rating>> entries = new LinkedList<>(map.entrySet());
    Collections.sort(
      entries,
      (o1, o2) -> o2.getValue().compareTo(o1.getValue())
    );

    for (Map.Entry<Integer, Rating> entry : entries) {
      if (result.size() < limit) result.put(
        entry.getKey(),
        entry.getValue()
      ); else break;
    }
  }

  public void calcResult() {
    String moviename = "";
    String moviegenre = "";
    String movielink = "";
    int i = 0;
    ID = new ArrayList<>(result.keySet());
    movies.searchName(ID);
    names = movies.getMoviesName();
    genres = movies.getMovieGenres();

    try {
      for (Integer key : result.keySet()) {
        File linkfile = new File("./data/links.dat");
        FileReader fileReader = new FileReader(linkfile);
        BufferedReader bufReader = new BufferedReader(fileReader);
        String data = "";

        moviename = names.get(i);
        moviegenre = genres.get(i);
        i += 1;

        while ((data = bufReader.readLine()) != null) {
          String[] temp = data.split("::");
          if (key == Integer.parseInt(temp[0])) {
            movielink = temp[1];
            break;
          }
        }
        Movie movie = new Movie(
          moviename,
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

  public int numMoviesResult() {
    return this.result.size();
  }
}
