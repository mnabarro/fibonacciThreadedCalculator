package org.example;

public class MyThread extends Thread {

  int number;
  private long result;

  public MyThread(String name, int number) {
    super(name);
    this.number = number;
  }

  public long getResult() {
    return result;
  }

  public void run() {
    long finalNumber;
    try {
      finalNumber = Fibonacci.calculate(number);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    this.result = finalNumber;
  }
}
