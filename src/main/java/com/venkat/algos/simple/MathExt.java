package com.venkat.algos.simple;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.LongStream;

public class MathExt {

    public static final int VERY_LARGE_MAX_INT = 1 << 20;
    public static final int VERY_LARGE_MIN_INT = -1 * VERY_LARGE_MAX_INT;

    public static long min(long... vals) throws IllegalArgumentException {
        return Optional.ofNullable(vals)
                       .map(valsArr -> Arrays.stream(valsArr).min().getAsLong())
                       .orElseThrow(() -> new IllegalArgumentException("Arguments needed!"));
    }

    public static int min(int... vals) throws IllegalArgumentException {
        return Optional.ofNullable(vals)
                       .map(valsArr -> Arrays.stream(valsArr).min().getAsInt())
                       .orElseThrow(() -> new IllegalArgumentException("Arguments needed!"));
    }

    public static int max(int... vals) throws IllegalArgumentException {
        return Optional.ofNullable(vals)
                       .map(valsArr -> Arrays.stream(valsArr).max().getAsInt())
                       .orElseThrow(() -> new IllegalArgumentException("Arguments needed!"));
    }

    public static long factorial(long n) {
        return LongStream.rangeClosed(1, n).reduce((prod, num) -> prod * num).getAsLong();
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
    
    /**
     * Recursive algorithm:
     * if (a == 0)
     *      return b;
     *  else
     *      return (findGCD(b % a, a));
     *
     * @param a First number
     * @param b Second number
     * 
     * @return GDC of a & b
     */
    public static int gcd(int a, int b) {
        if (a == 0) {
            if (b == 0)
                throw new IllegalArgumentException("Both numbers can't be 0");
            else
                return b;
        }
        for (int r = b % a; r != 0; r = b % a) {
            b = a;
            a = r;
        }
        return a;
    }
    
    public static int gcd(int[] nums) {
        if (nums == null) {
            return 0;
        }
        return Arrays
                   .stream(nums)
                   .skip(1)
                   .reduce(nums[0], (currentGCD, n) -> gcd(currentGCD, n));
    }
    
    public static int gcdOfArgs(int... nums) {
        return gcd(nums);
    }

    public static int lcm(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int lcm = 1;
        int[] numsForWork = Arrays.copyOf(nums, nums.length);
        int currentPrimeDivisor = 2;
        while (Arrays.stream(numsForWork).anyMatch(x -> Math.abs(x) > 1)) {
            boolean currentDivisorWorks = false;
            for (int i = 0 ; i < numsForWork.length ; i++) {
                if (numsForWork[i] % currentPrimeDivisor == 0) {
                    numsForWork[i] /= currentPrimeDivisor;
                    currentDivisorWorks = true;
                }
            }
            if (currentDivisorWorks) {
                lcm *= currentPrimeDivisor;
            } else {
                currentPrimeDivisor = nextPositivePrime(currentPrimeDivisor);
            }
        }
        return lcm * Math.abs(Arrays.stream(numsForWork).reduce((x, y) -> x * y).getAsInt());
    }

    public static int lcm2(int[] nums) {
        return Optional.ofNullable(nums)
                .map(numsArr -> {
                	return Arrays.stream(numsArr).reduce((x, y) -> x * y).getAsInt()/gcd(nums);
                })
                .orElseThrow(() -> new IllegalArgumentException("nums required!"));
    }
    
    public static int lcmOfArgs(int... args) {
        return lcm(args);
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
     *       2. int num = originalNum;
     *          while (num > 10) num /= 100;
     *          return originalNum >= 10 && num > 0;
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
    }

    /**
     * From GFG: https://www.geeksforgeeks.org/primality-test-set-1-introduction-and-school-method/
     * 
     * We can keep incrementing by 6 because every prime number is of the form:
     *    6k ± 1
     *    This is because every number can be expressed as:
     *    6k + i for some integer k and for i = -1, 0, 1, 2, 3, 4...
     *    and 6k, (6k + 2), (6k + 3), (6k + 4) are divisible by 2 or 3...
     *    Only (6k + 1), (6k - 1) are not divisible, so can be potential candidates...
     * 
     * @param n           The number to be determined prime or not...
     * @return            true if n is prime, false otherwise...
     */
    public static boolean isPositivePrime(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Please supply a positive number!");
        boolean isPrimeCandidate = n < 4 || (n % 2 != 0 && n % 3 != 0);
        for (int i = 5 ; isPrimeCandidate && i * i <= n ; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                isPrimeCandidate = false;
                break;
            }
        }
        return isPrimeCandidate;
    }

    public static int nextPositivePrime(int n) {
        if (n < 0)
            throw new IllegalArgumentException("Please supply a positive number!");
        if (n < 3)
            return ++n;
        int maxK = VERY_LARGE_MAX_INT / 6;
        for (int k = n / 6 + 1; k < maxK; k++) {
            int primeCandidate = 6 * k - 1;
            if (primeCandidate > n && isPositivePrime(primeCandidate))
                return primeCandidate;
            primeCandidate = 6 * k + 1;
            if (isPositivePrime(primeCandidate))
                return primeCandidate;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {2, 3, 5};
        System.out.format("Nums=%s, LCM=%d%n", Arrays.toString(nums), lcm2(nums));
    }

}
