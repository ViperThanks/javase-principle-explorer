package com.yiren.tmp;

import com.sun.javafx.scene.control.ReadOnlyUnbackedObservableList;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 31/12/2023
 */
public class Demo1 {

  private static final Scanner sc = new Scanner(System.in);
  public static void main(String[] args) {
    /*double sum = 0;
    int loopTimes = sc.nextInt();
    for (int i = 0; i < loopTimes; i++) {
      System.out.printf("%.5f\n", calculateSum(sc.nextDouble()));
    }*/
    System.out.println(BigDecimal.valueOf(1e-10).toPlainString());
  }

  private static double calculateSum(double value) {

    double sum = 0,x;
    int count = 1;
    while (true) {
      x = rule(value, count++);
      if (Math.abs(x) < 1e-5) {
        break;
      }
      sum += x;
    }
    return sum;
  }

  private static double rule(double value, int n) {
    return Math.pow(-1, n + 1) * n / Math.pow(value, n);
  }

}
