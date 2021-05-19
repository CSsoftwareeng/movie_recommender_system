package com.recommend.app;

import com.recommend.app.RatingCalculator;
import com.recommend.utils.errors.*;
import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieBasedRecommController {

  @GetMapping("/movies/recommendations")
  public List<Movie> movieBasedAPI(
    @RequestBody Map<String, String> requestParams
  ) {
    try {
      String title = requestParams.get("title");
      String limit_str = requestParams.get("limit");
      int limit = 10;
      // Arguments arg = new Arguments(title, limit_str);
      if(limit_str.length()!=0)
        limit = Integer.parseInt(limit_str);
      
      MovieList movielist = new MovieList();
      UserList userlist = new UserList();

      movielist.searchFavoriteMovie(title);
      userlist.searchFavoriteUsers(title);

      RatingCalculator rating = new RatingCalculator(movielist, userlist);
      rating.calcAverageHash(limit);
      rating.calcResult();
      return rating.getMoviesResult();
    } catch (MovieNotExistError e) {} catch (ArgNotExistError e) {} catch (
      ArgCntError e
    ) {}
    return null;
  }
}