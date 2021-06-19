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
    
    List<Review> matchedUsersReviews = reviewRepository.findByUseridIn(users.matchedUsers);

    map = new HashMap<>();
    result = new LinkedHashMap<>();

    for (Review review : matchedUsersReviews) {
      int movie = review.movieid;
      int rating = review.rating;

      if (movies.findID(movie)) {
        if (map.containsKey(movie)) {
          int tsum = map.get(movie).getSum() + rating;
          int tcount = map.get(movie).getCount() + 1;
          map.put(movie, new Rating(tsum, tcount, 3));
        } else {
          map.put(movie, new Rating(rating, 1, 3));
        }
      }
    }

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
    map = new HashMap<>();
    if(userfilter)
      result = new LinkedHashMap<>();
    List<Review> reviews;
    if (userfilter) {
      reviews = reviewRepository.findByUseridIn(users.favoriteUsers);
    } else {
      reviews = reviewRepository.findAll();
    }

    for (Review review : reviews) {
      int user = review.userid;
      int movie = review.movieid;
      int rating = review.rating;
      
      if (map.containsKey(movie)) {
        int match = map.get(movie).match;
        int tsum = map.get(movie).getSum() + rating;
        int tcount = map.get(movie).getCount() + 1;
        map.put(movie, new Rating(tsum, tcount, match));
      } else {
        int match = movies.countMathcedGenres(movie);
        map.put(movie, new Rating(rating, 1, match));
      }
    }
    map.remove(movies.favoriteMovieID);
    if (!userfilter) {
      List<Integer> keys = new ArrayList<>(result.keySet());
      for (Integer key : keys) {
        map.remove(key);
      }
    }

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
      String link = "-";
      String poster = "-";
      Link linkDoc = linkRepository.findByMovieid(ID.get(i));
      Poster posterDoc = posterRepository.findByMovieid(ID.get(i));
      if (linkDoc != null)
        link = linkDoc.link;
      if (posterDoc != null)
        poster = posterDoc.poster;


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
