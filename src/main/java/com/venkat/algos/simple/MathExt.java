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
        return (int) Math.log10((double) n);
    }

    /**
     * This is one of O(log n) solution to find if number of digits are even or odd...
     * Tries to compare if the number is in range: 10-100 or 1000-10000 or 100000-1000000 etc... as all these numbers have evendigits in number!
     * 
     * Other solution:
     *       1. Single line solution - return Math.log10((double) num) % 2 == 0;
     *       2. boolean numDigitsEven = false;
     *          while (num > 0) {
     *              num /= 10;
     *              numDigitsEven = !numDigitsEven;
     *          }
     * 
     * From leetcode - https://leetcode.com/explore/learn/card/fun-with-arrays/521/introduction/3237/
     * 
     * @param num          The number for which we want to determine if it has even number of digits...
     * @return             true if the number of digits in number is even, false otherwise...
     */
    public static boolean isNumDigitsEven(int num) {
        boolean numDigitsEven = false;
        for (int oddNumberedTenth = 100; num > oddNumberedTenth && oddNumberedTenth < Integer.MAX_VALUE; oddNumberedTenth *= 100) {
            numDigitsEven = num >= oddNumberedTenth/10;
        }
        return numDigitsEven;
    }

}
