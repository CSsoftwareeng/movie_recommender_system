package com.recommend.app;


import java.util.*;

import org.junit.Test;
import org.junit.Assert;

public class RatingCalculatorTest {


        


    @Test
    public void testcalcAverageHash() {
        UserList users = new UserList();
        List <String> genres = new ArrayList <String>();
        genres.add("Comedy");
        MovieList movies = new MovieList(genres);
	    users.searchSimilarUser("F","25","4");
        RatingCalculator rating = new RatingCalculator(movies,users);
        Assert.assertTrue(rating.result.containsKey(171));
        Assert.assertFalse(rating.result.containsKey(1));
        Assert.assertEquals(rating.result.size(),10);
    }

    @Test
    public void testShowResult(){
        UserList users = new UserList();
        List <String> genres = new ArrayList <String>();
        genres.add("Comedy");
        MovieList movies = new MovieList(genres);
	    users.searchSimilarUser("F","25","4");
        RatingCalculator rating = new RatingCalculator(movies,users);
        rating.showResult(movies);
        Assert.assertTrue(rating.ID.contains(171));
        Assert.assertFalse(rating.ID.contains(1));
        Assert.assertTrue(rating.names.contains("Jeffrey (1995)"));
        Assert.assertFalse(rating.names.contains("Toy Story (1995)"));
    }


    
}