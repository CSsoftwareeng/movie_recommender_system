package com.recommend.app;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;

public interface MovieRepository extends MongoRepository<Movie, String> {

  public Movie findByMovieid(Integer movieid);
  public List<Movie> findByTitle(String title);
  public List<Movie> findByGenres(String genres);
  @Aggregation("{ '$project': { '_id' : '$movieid' } }")
  List<Integer> findAllMovieid();
  @Aggregation("{ '$match': {'genres': {'$regex': ?0} } }, { '$project': { '_id' : '$movieid' } }")
  List<Integer> findMovieidByGenresRegex(String genreRegex);

  @Query("{ 'genres': {'$regex': ?0} }")
  List<Movie> findByGenresRegex(String genreRegex);
  
  @Query(value = "{'movieid': {'$in' : ?0 } }", fields = "{'_id': 0}")
  List<Movie> findAllByMovieidIn(List<Integer> movieids);
  
}