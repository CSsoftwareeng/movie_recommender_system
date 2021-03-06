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
  @Autowired
  private PosterRepository posterRepository;
  @Autowired
  private LinkRepository linkRepository;

  TreeSet<Integer> movies = new TreeSet<Integer>();
  List<String> movieName = new ArrayList<String>();
  List<String> movieGenres = new ArrayList<String>();
  List<String> favoriteGenres = new ArrayList<String>();
  List<TreeSet<Integer>> similarMovies = new ArrayList<TreeSet<Integer>>();
  Integer favoriteMovieID;
  HashMap<Integer, String> titles = new HashMap<>();
  HashMap<Integer, String> genres = new HashMap<>();
  List moviesResult = new ArrayList<Movie>();

  public MovieList() {}

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
      regex = regex.substring(0, regex.length()-1);
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

  public Movie getMovieFromTitle(String title)
    throws MovieNotExistError {
    String raw_title = null;
    raw_title =
      title
        .trim()
        .toLowerCase();
    String regex = ".*"+raw_title+".*";
    Movie movie = movieRepository.findOneByTitleRegex(regex);
    if (movie == null) {
      throw new MovieNotExistError(title);
    }
    return movie;
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
    regex = regex.substring(0, regex.length()-1);

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
    Movie movie = getMovieFromTitle(title);
    this.favoriteGenres = Arrays.asList(movie.genres.split("\\|"));
    this.favoriteMovieID = movie.movieid;
    searchSimilarID(favoriteGenres);
  }

  public int countMathcedGenres(int movieID) {
    int match = 0;
    for (int i = 0; i < favoriteGenres.size(); i++) {
      if (similarMovies.get(i).contains(movieID)) match = i + 1;
    }
    return match;
  }

  public void searchAllMovies() {
    movies = new TreeSet<Integer>();
    List<Movie> allMovies = movieRepository.findAll();

    for(Movie moviedata : allMovies) {
      String link = "-";
      String poster = "-";
      Link linkDoc = linkRepository.findByMovieid(moviedata.movieid);
      Poster posterDoc = posterRepository.findByMovieid(moviedata.movieid);
      if (linkDoc != null)
        link = linkDoc.link;
      if (posterDoc != null)
        poster = posterDoc.poster;


      Movies movie = new Movies(
              moviedata.title,
              moviedata.genres,
              "(http://www.imdb.com/title/tt" + link + ")",
              poster

      );
      this.moviesResult.add(movie);
    }
  }

  public List getMoviesResult() {
    return this.moviesResult;
  }

}
