package com.recommend.app;

import com.recommend.utils.errors.ArgCntError;
import java.io.*;
import java.util.*;
import jdk.jfr.Timestamp;
import org.junit.*;

public class MainClassTest {

  ByteArrayOutputStream output = new ByteArrayOutputStream();
  PrintStream originalOutput = System.out;

  @Before
  public void setOutput() {
    System.setOut(new PrintStream(output));
  }

  @After
  public void restoreOutput() {
    System.setOut(originalOutput);
  }
  // @Test
  // public void testArgCntErrorUnder() {
  //   String[] args1 = { "1", "2" };
  //   MainClass.main(args1);
  //   Assert.assertTrue(output.toString().contains("[ERROR : ArgCntError]"));
  // }

  // @Test
  // public void testArgCntErrorOver() {
  //   String[] args1 = { "1", "2", "3", "4", "5" };
  //   MainClass.main(args1);
  //   Assert.assertTrue(output.toString().contains("[ERROR : ArgCntError]"));
  // }

  // @Test
  // public void testMainClassFourInputs() {
  //   String[] args1 = { "F", "25", "Grad student", "Action|Comedy" };

  //   MainClass.main(args1);
  //   Assert.assertTrue(
  //     output
  //       .toString()
  //       .contains(
  //         "Dead Presidents (1995) (http://www.imdb.com/title/tt0112819) Rating : 5.0"
  //       )
  //   );
  // }

  // @Test
  // public void testMainClassThreeInputs() {
  //   String[] args1 = { "F", "25", "Grad student" };

  //   MainClass.main(args1);
  //   Assert.assertTrue(
  //     output
  //       .toString()
  //       .contains(
  //         "Chungking Express (1994) (http://www.imdb.com/title/tt0109424) Rating : 5.0"
  //       )
  //   );
  // }
}
