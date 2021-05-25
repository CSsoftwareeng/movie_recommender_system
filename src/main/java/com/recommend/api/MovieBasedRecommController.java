package com.recommend.app;

import com.recommend.app.RatingCalculator;
import com.recommend.utils.errors.*;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class MovieBasedRecommController {

  @GetMapping("/movies/recommendations")
  public List<Movie> movieBasedAPI(
    @RequestBody Map<String, Object> requestBody
  ) {
    try {
      int bodysize = requestBody.size();
      if (bodysize == 0 || bodysize > 2) throw new ArgCntError(
        (Integer) bodysize
      );
      String title = (String) requestBody.get("title");
      if (title == null) throw new ArgMissingError("title");

      Integer limit = (Integer) requestBody.get("limit");
      if (limit == null) {
        if (bodysize == 2) throw new WrongArgError("movie");
        limit = 10;
      }
      if (limit <= 0) {
        throw new WrongArgError("limit");
      }

      MovieList movielist = new MovieList();
      UserList userlist = new UserList();

      movielist.registerFavoriteMovie(title);
      userlist.searchFavoriteUsers(title);

      RatingCalculator rating = new RatingCalculator(movielist, userlist);
      rating.rankGenreBasedRating(limit, true);
      if (rating.numMoviesResult() < limit) rating.rankGenreBasedRating(
        limit,
        false
      );
      rating.calcResult();
      return rating.getMoviesResult();
    } catch (MovieNotExistError e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    } catch (ArgCntError e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    } catch (ArgMissingError e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    } catch (WrongArgError e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}
