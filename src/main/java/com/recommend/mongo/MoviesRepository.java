package com.recommend.app;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;

public interface MoviesRepository extends MongoRepository<Movies, String> {

  public Movies findByMovieid(Integer movieid);
  public List<Movies> findByTitle(String title);
  public List<Movies> findByGenre(String genre);
  @Aggregation("{ '$project': { '_id' : '$movieid' } }")
  List<Integer> findAllMovieid();
  @Aggregation("{ '$match': {'genre': {'$regex': ?0} } }, { '$project': { '_id' : '$movieid' } }")
  List<Integer> findMovieidByGenreRegex(String genreRegex);

  @Query("{ 'genre': {'$regex': ?0} }")
  List<Movies> findByGenreRegex(String genreRegex);
  
  @Query(value = "{'movieid': {'$in' : ?0 } }", fields = "{'_id': 0}")
  List<Movies> findAllByMovieidIn(List<Integer> movieids);
  
}