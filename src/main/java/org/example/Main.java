package org.example;

public class Main {
  public static class MyThread extends Thread {
    int number = 0;
    public MyThread(String name, int number) {
      super(name);
      this.number = number;
    }

    public void run(){

      try {
        Fibonacci.calculate(number);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      System.out.println("MyThread running");
    }
  }
  public static void main(String[] args) throws Exception {
    MyThread myThread = new MyThread("fibo.1",3);
    MyThread myThread2 = new MyThread("fibo.1",3);
    MyThread myThread3 = new MyThread("fibo.1",3);
    myThread.start();
    myThread2.start();
    myThread3.start();

    System.out.println("fibo =" + Fibonacci.calculate(100));
  }
}
