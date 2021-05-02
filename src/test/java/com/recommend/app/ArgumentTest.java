package com.recommend.app;

import com.recommend.utils.errors.ArgCntError;
import com.recommend.utils.errors.ArgNotExistError;
import java.util.*;
import jdk.jfr.Timestamp;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class ArgumentTest {

  @Test(expected = ArgNotExistError.class)
  public void testArgNotExistErrorGender() {
    Arguments args1 = new Arguments("str_gender", "", "", "Action");
  }

  @Test(expected = ArgNotExistError.class)
  public void testArgNotExistErrorage() {
    Arguments args1 = new Arguments("", "-1", "", "Action");
  }

  @Test(expected = ArgNotExistError.class)
  public void testArgNotExistErrorOccupation() {
    Arguments args1 = new Arguments("", "", "str_occ", "Action");
  }

  @Test(expected = ArgNotExistError.class)
  public void testArgNotExistErrorGenreInvalid() {
    Arguments args1 = new Arguments("", "", "", "str_genre");
  }

  @Test
  public void testSetMap() {
    Arguments args1 = new Arguments();
    args1.setMap();
    Assert.assertEquals("Result", args1.OCCUPATIONS_MAP.size(), 47);
    Assert.assertEquals("Result", args1.GENRE_MAP.size(), 20);
  }

  @Test
  public void testSetArgs() {
    Arguments args1 = new Arguments();
    Arguments args2 = new Arguments();
    args1.setArgs("1", "2", "3");
    args2.setArgs("4", "5", "6", "7");
    Assert.assertEquals("Result", args1.gender, "1");
    Assert.assertEquals("Result", args1.age, "2");
    Assert.assertEquals("Result", args1.raw_occupation, "3");
    Assert.assertEquals("Result", args2.gender, "4");
    Assert.assertEquals("Result", args2.age, "5");
    Assert.assertEquals("Result", args2.raw_occupation, "6");
    Assert.assertEquals("Result", args2.raw_genres, "7");
  }

  @Test
  public void testParseGender() {
    Arguments args1 = new Arguments();
    Arguments args2 = new Arguments();
    Arguments args3 = new Arguments();

    args1.setArgs("f", "25", "Grad student");
    args2.setArgs("m", "25", "Grad student", "Action|Comedy");
    args3.setArgs("", "25", "Grad student");

    args1.parseGender();
    args2.parseGender();
    args3.parseGender();

    Assert.assertEquals("Result", args1.gender, "F");
    Assert.assertEquals("Result", args2.gender, "M");
    Assert.assertEquals("Result", args3.gender, "");
  }

  @Test
  public void testParseAge() {
    Arguments args1 = new Arguments();
    Arguments args2 = new Arguments();
    Arguments args3 = new Arguments();
    Arguments args4 = new Arguments();
    Arguments args5 = new Arguments();
    Arguments args6 = new Arguments();
    Arguments args7 = new Arguments();

    args1.age = "10";
    args2.age = "20";
    args3.age = "30";
    args4.age = "40";
    args5.age = "48";
    args6.age = "52";
    args7.age = "60";

    args1.parseAge();
    args2.parseAge();
    args3.parseAge();
    args4.parseAge();
    args5.parseAge();
    args6.parseAge();
    args7.parseAge();

    Assert.assertEquals("Result", args1.age, "1");
    Assert.assertEquals("Result", args2.age, "18");
    Assert.assertEquals("Result", args3.age, "25");
    Assert.assertEquals("Result", args4.age, "35");
    Assert.assertEquals("Result", args5.age, "45");
    Assert.assertEquals("Result", args6.age, "50");
    Assert.assertEquals("Result", args7.age, "56");
  }

  @Test
  public void testPrintArgs() {
    Arguments args1 = new Arguments("F", "25", "grad student", "action|comedy");
    args1.printArgs();
  }

  @Test
  public void testGetMethods() {
    Arguments args1 = new Arguments("F", "25", "grad student", "action|comedy");
    List<String> expected = Arrays.asList("Action", "Comedy");
    Assert.assertEquals("4", args1.getOccupation());
    Assert.assertEquals(expected, args1.getGenres());
  }
}
