package com.recommend.app;

import java.util.*;
import org.junit.Assert;
import org.junit.Test;


public class AvgRatingTest {

  @Test
  public void testMethod() {
    AvgRating rating = new AvgRating(10, 5);
    Assert.assertEquals(10, rating.getSum());
    Assert.assertEquals(5, rating.getCount());
    rating.setAverage();
    Assert.assertTrue(rating.getAverage()==2.0);
  }
}
