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

}
