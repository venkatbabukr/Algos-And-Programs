package com.venkat.algos.simple;

import java.util.stream.LongStream;

public class MathExt {
    
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
                long figNminus1 = 1;
                for (int i = 2 ; i <= n ; i++) {
                    fib = fibNminus2 + figNminus1;
                    fibNminus2 = figNminus1;
                    figNminus1 = fib;
                }
        }
        return fib;
    }

    public static long catalan(long n) {
    	return factorial(2 * n)/(factorial(n + 1) * factorial(n));
    }

}
