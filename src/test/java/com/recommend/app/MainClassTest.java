package com.recommend.app;

import com.recommend.utils.errors.ArgCntError;
import java.util.*;
import jdk.jfr.Timestamp;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

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
}
