package com.venkat.algos.simple;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.LongStream;

public class MathExt {

    public static long min(long... vals) throws IllegalArgumentException {
        return Optional.ofNullable(vals)
                       .map(valsArr -> Arrays.stream(valsArr).min())
                       .get()
                       .orElseThrow(() -> new IllegalArgumentException("Arguments needed!"));
    }

    public static int min(int... vals) throws IllegalArgumentException {
        return Optional.ofNullable(vals)
                       .map(valsArr -> Arrays.stream(valsArr).min())
                       .get()
                       .orElseThrow(() -> new IllegalArgumentException("Arguments needed!"));
    }

    public static long factorial(long n) {
        return LongStream.rangeClosed(0, n).reduce(1, (prod, num) -> prod * num);
    }

    public static long fib(int n) {
        long fib = 0;
        switch (n) {
            case 0:
                fib = 0;
                break;
            case 1:
                fib = 1;
                break;
            default:
                long fibNminus2 = 0;
                long fibNminus1 = 1;
                for (int i = 2 ; i <= n ; i++) {
                    fib = fibNminus2 + fibNminus1;
                    fibNminus2 = fibNminus1;
                    fibNminus1 = fib;
                }
        }
        return fib;
    }

    public static long catalan(long n) {
    	return factorial(2 * n)/(factorial(n + 1) * factorial(n));
    }
    
    public static int findNumDigits(int n) {
        n = Math.abs(n);
        return n == 0 ? 1 : (int) Math.log10((double) n) + 1;
    }

    /**
     * This is one of O(log n) solution to find if number of digits are even or odd...
     * Tries to compare if the number is in range: 10-100 or 1000-10000 or 100000-1000000 etc... as all these numbers have evendigits in number!
     * 
     * Other solution:
     *       1. Single line solution - return Math.log10((double) num) % 2 == 0;
     *
     *       2. while (num > 10) num /= 100;
     *          return num > 0;
     * 
     * From leetcode - https://leetcode.com/explore/learn/card/fun-with-arrays/521/introduction/3237/
     * 
     * @param num          The number for which we want to determine if it has even number of digits...
     * @return             true if the number of digits in number is even, false otherwise...
     */
    public static boolean isNumDigitsEven(int num) {
        num = Math.abs(num);
        int nearestGreaterHundredth = 1;
        for (nearestGreaterHundredth = 1 ; num > nearestGreaterHundredth && nearestGreaterHundredth < Integer.MAX_VALUE / 100 ; nearestGreaterHundredth *= 100);
        return num != 0 && num >= nearestGreaterHundredth / 10 && num < nearestGreaterHundredth;
        /* 
         * Other solution based on above logic!!!
         * while (num > 10) num /= 100;
         * return num > 0;
         */
    }

}
