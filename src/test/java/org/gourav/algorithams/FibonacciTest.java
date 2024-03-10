package org.gourav.algorithams;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FibonacciTest {

    Fibonacci fibonacci = new Fibonacci();

    @Test
    public void fibonacciCalculateTest(){
        Assertions.assertEquals(34,fibonacci.fibonacciCalculate(9));
    }
}
