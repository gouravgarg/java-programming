package org.gourav.algorithams;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testing Fibonacci")
public class FibonacciTest {

    Fibonacci fibonacci = new Fibonacci();

    @Test
    @DisplayName("Testing Fibonacci 9 result would be 34")
    public void fibonacciCalculateTest(){
        Assertions.assertEquals(34,fibonacci.fibonacciCalculate(9));
    }
}
