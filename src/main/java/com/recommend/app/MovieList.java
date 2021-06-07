package com.recommend.app;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.recommend.utils.errors.MovieNotExistError;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

@Component
public class MovieList {
  @Autowired
  private MovieRepository movieRepository;
  TreeSet<Integer> movies = new TreeSet<Integer>();
  List<String> movieName = new ArrayList<String>();
  List<String> movieGenres = new ArrayList<String>();
  List<String> favoriteGenres = new ArrayList<String>();
  List<TreeSet<Integer>> similarMovies = new ArrayList<TreeSet<Integer>>();
  Integer favoriteMovieID;

  public MovieList() {}

  public MovieList(List<String> genres) {
    searchID(genres);
  }


  void searchID(List<String> genres) throws MovieNotExistError {
    movies = new TreeSet<Integer>();
    int genres_num = genres.size();
    List<Integer> temp;
    if (genres.isEmpty() || genres.contains("")) {
        temp = movieRepository.findAllMovieid();
        movies = new TreeSet<Integer>(temp);
    } else{
      String regex = "";
      for(String genre: genres)  {
        regex += genre + "|";
      }
      if (regex.length() > 0) {
        regex = regex.substring(0, regex.length()-1);
      }
      temp = movieRepository.findMovieidByGenresRegex(regex);
      movies = new TreeSet<Integer>(temp);
    }

    if (!movies.isEmpty()) {
      return;
    }
    throw new MovieNotExistError(genres);
  }

  public boolean findID(int MovieID) {
    return movies.contains(MovieID);
  }

  public TreeSet<Integer> getMoviesID() {
    return movies;
  }

  public void searchName(List<Integer> ID) {
    movieName = new ArrayList<String>();
    movieGenres = new ArrayList<String>();
    
    for (Integer id: ID)
    {
      Movie movie = movieRepository.findByMovieid(id);
      movieName.add(movie.title);
      movieGenres.add(movie.genres);
    }
  }

  public List<String> getMoviesName() {
    return movieName;
  }

  public List<String> getMovieGenres() {
    return movieGenres;
  }

  public void searchSimilarID(List<String> genres) {
    similarMovies = new ArrayList<TreeSet<Integer>>();
    int genres_num = genres.size();
    for (int i = 0; i < genres_num; i++) similarMovies.add(
      new TreeSet<Integer>()
    );

    String regex = "";
    for(String genre: genres)  {
      regex += genre + "|";
    }
    if (regex.length() > 0) {
      regex = regex.substring(0, regex.length()-1);
    }    

    List<Movie> temp = movieRepository.findByGenresRegex(regex);
    
    for(Movie movie : temp) {
      int count = 0;
      for (int i = 0; i < genres_num; i++) {
        if (movie.genres.contains(genres.get(i))) {
          count++;
        }
      }
      if (count > 0) {
        similarMovies.get(count - 1).add(movie.movieid);
      }  
    }
  }

  public void registerFavoriteMovie(String title) {
    this.favoriteGenres = Tool.getMovieGenre(title);
    this.favoriteMovieID = Tool.getMovieID(title);
    searchSimilarID(favoriteGenres);
  }

  public int countMathcedGenres(int movieID) {
    int match = 0;
    for (int i = 0; i < favoriteGenres.size(); i++) {
      if (similarMovies.get(i).contains(movieID)) match = i + 1;
    }
    return match;
  }
}
