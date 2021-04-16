package com.gourav.springboot.al;

public class Prime {
    public static void main(String[] args) {


        int count = 0;
        for (int i = 6; i < 60; i = (i + 6)) {
            int j = i;

            if (isPrime(j + 1)) {
                System.out.println(j + 1);
                count++;
            }
            if (isPrime(j - 1)) {
                System.out.println(j - 1);
                count++;
            }

        }
        System.out.println("Count=" + count);
    }


    static boolean isPrime(int n) {

//        // Check if number is less than
//        // equal to 1
//        if (n <= 1)
//            return false;
//
//            // Check if number is 2
//        else if (n == 2)
//            return true;
//
//            // Check if n is a multiple of 2
//        else if (n % 2 == 0)
//            return false;

        // If not, then just check the odds
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
