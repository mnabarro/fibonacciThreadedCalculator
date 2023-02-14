package org.example;

import static java.lang.Thread.sleep;

public class Fibonacci {
  public static long calculate ( int times) throws InterruptedException {
    long[] accum = {0,1};
    long fibo = 0;
    for ( int cnt = 0;  cnt < times; cnt++ ) {
    fibo = accum[1] + accum[0];
    accum[0] = accum[1];
    accum[1] = fibo;
    sleep(100);
//      System.out.println(fibo);
    }
    return fibo;
  }
}
