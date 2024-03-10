package org.gourav.algorithams;

public class Fibonacci {

    public int fibonacciCalculate(int n) {
        if (n <= 1)
            return n;
        return fibonacciCalculate(n - 1) + fibonacciCalculate(n - 2);
    }

    public static void main(String args[]) {
        Fibonacci fibonacci =new Fibonacci();
        System.out.println(fibonacci.fibonacciCalculate(9));
    }
}