package com.recommend.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.*;

@Component
public class RatingCalculator {

  @Autowired
  private ReviewRepository reviewRepository;
  @Autowired
  private PosterRepository posterRepository;
  @Autowired
  private LinkRepository linkRepository;

  HashMap<Integer, Rating> map = new HashMap<>();
  LinkedHashMap<Integer, Rating> result = new LinkedHashMap<>();
  List<String> names;
  List<String> genres;
  List<Integer> ID;
  List moviesResult = new ArrayList<Movies>();
  MovieList movies;
  UserList users;

  void setLists(MovieList movieList, UserList userList) {
    this.movies = movieList;
    this.users = userList;
  }

  void rankUserBasedRating(int limit) {
    try {
      result = new LinkedHashMap<>();
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
      result = new LinkedHashMap<>();
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
    moviesResult = new ArrayList<Movies>();
    ID = new ArrayList<>(result.keySet());
    movies.searchName(ID);
    names = movies.getMoviesName();
    genres = movies.getMovieGenres();

    int sizeID = ID.size();

    for (int i = 0; i < sizeID; i++) {
      Link linkDoc = linkRepository.findByMovieid(ID.get(i));
      String link;
      String poster = posterRepository.findOneByMovieid(ID.get(i));
      if (linkDoc == null)
        link = "";
      else
        link = linkDoc.link;
      if (poster == null)
        poster = "";

      Movies movie = new Movies(
        names.get(i),
        genres.get(i),
        "(http://www.imdb.com/title/tt" + link + ")",
        poster
      );

      this.moviesResult.add(movie);

    }
  }

  public List getMoviesResult() {
    return this.moviesResult;
  }

  public int numMoviesResult() {
    return this.result.size();
  }
}
