package org.example;

public class MyThread extends Thread {

  int number;

  public MyThread(String name, int number) {
    super(name);
    this.number = number;
  }

  public void run() {
    long finalNumber;
    try {
    finalNumber =  Fibonacci.calculate(number);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println( "Thread [" + this.getName() +  "] -> Fibonacci number for " + number + " is " + finalNumber);
  }
}
