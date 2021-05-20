package com.recommend.app;

class Rating implements Comparable<Rating> {

  int sum;
  int count;
  double average;
  int match;

  public Rating(int a, int b, int c) {
    this.sum = a;
    this.count = b;
    this.match = c;
    this.average = (double) a / b;
  }

  public int getSum() {
    return this.sum;
  }

  public int getCount() {
    return this.count;
  }

  public double getAverage() {
    return this.average;
  }

  public int getMatch() {
    return this.match;
  }

  @Override
  public int compareTo(Rating t) {
    if (this.match > t.getMatch()) {
      return 1;
    } else if (this.match < t.getMatch()) {
      return -1;
    } else {
      if (this.average > t.getAverage()) {
        return 1;
      } else if (this.average < t.getAverage()) {
        return -1;
      }
    }
    return 0;
  }
}