package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Main {

  static ArrayList<MyThread> threadsCollection = new ArrayList<>();
  static int getRandomIntInRange(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min) + min;
  }
  public static void main(String[] args) {

  threadsCollection.add(new MyThread("Thread #"+ threadsCollection.size(), getRandomIntInRange(5,50)));
  threadsCollection.get(0).start();
  }
}
