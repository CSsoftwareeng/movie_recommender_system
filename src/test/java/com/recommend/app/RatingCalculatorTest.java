package com.recommend.app;


import java.util.*;

import org.junit.Test;
import org.junit.Assert;

public class RatingCalculatorTest {

    @Test
    public void testgetSum(){
        Rating rating = new Rating(10,5,2);
        Assert.assertEquals(rating.getSum(),10);
    }

    @Test
    public void testgetCount(){
        Rating rating = new Rating(10,5,2);
        Assert.assertEquals(rating.getCount(),5);
    }

    @Test
    public void testgetAverage(){
        Rating rating = new Rating(10,5,2);
        int exp = (int)2.0;
        int act = (int)rating.getAverage();
        Assert.assertEquals(exp, act);
    }

    @Test
    public void testgetMatch(){
        Rating rating = new Rating(10,5,2);
        Assert.assertEquals(rating.getMatch(),2);
    }

    @Test
    public void testcompareto(){
        Rating rating1 = new Rating(10,2,2);
        Rating rating2 = new Rating(10,5,3);
        Rating rating3 = new Rating(10,2,3);
        Rating[] arr = new Rating[]{rating1,rating2,rating3};
        Arrays.sort(arr);
        Assert.assertEquals(rating1,arr[0]);
        Assert.assertEquals(rating2,arr[1]);
        Assert.assertEquals(rating3,arr[2]);
    }

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