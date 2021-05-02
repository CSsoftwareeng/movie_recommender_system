package com.recommend.app;

import com.recommend.utils.errors.ArgCntError;
import java.io.*;
import java.util.*;
import jdk.jfr.Timestamp;
import org.junit.*;

public class MainClassTest {

  @Test(expected = ArgCntError.class)
  public void testArgCntErrorUnder() {
    String[] args1 = { "1", "2" };
    MainClass.main(args1);
  }

  @Test(expected = ArgCntError.class)
  public void testArgCntErrorOver() {
    String[] args1 = { "1", "2", "3", "4", "5" };
    MainClass.main(args1);
  }

  @Test
  public void testMainClassFourInputs() {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream originalOutput = System.out;
    String[] args1 = { "F", "25", "Grad student", "Action|Comedy" };
    System.setOut(new PrintStream(output));
    MainClass.main(args1);
    Assert.assertTrue(
      output
        .toString()
        .contains(
          "Dead Presidents (1995) (http://www.imdb.com/title/tt0112819) Rating : 5.0"
        )
    );
    System.setOut(originalOutput);
  }

  @Test
  public void testMainClassThreeInputs() {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream originalOutput = System.out;
    String[] args1 = { "F", "25", "Grad student" };
    System.setOut(new PrintStream(output));
    MainClass.main(args1);
    Assert.assertTrue(
      output
        .toString()
        .contains(
          "Chungking Express (1994) (http://www.imdb.com/title/tt0109424) Rating : 5.0"
        )
    );
    System.setOut(originalOutput);
  }
}
