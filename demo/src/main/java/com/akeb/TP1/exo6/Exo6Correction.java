package com.akeb.TP1.exo6;

public class Exo6Correction {
    public static String fizzBuzz(int n) {
        if (n <= 1) {
            throw new IllegalArgumentException("n must be greater than 1"); // Fixed: improved error message clarity
        }
        if (n % 15 == 0) {
            return "FizzBuzz";
        }
        if (n % 3 == 0) {
            return "Fizz";
        }
        if (n % 5 == 0) {
            return "Buzz";
        }
        return String.valueOf(n);
    }
}
