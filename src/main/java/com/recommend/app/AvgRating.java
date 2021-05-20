package com.recommend.app;

class AvgRating {

  int sum;
  int count;
  double average;

  public AvgRating(int sum, int count) {
    this.sum = sum;
    this.count = count;
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

  public void setAverage() {
    this.average = (double) (this.sum) / (this.count);
  }
}