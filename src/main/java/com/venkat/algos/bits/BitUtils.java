package com.venkat.algos.bits;

public final class BitUtils {
    
    private BitUtils() { }

    public static long countOnesSetInNumber(long number) {
    	int onesCount = 0;
    	// This is Brian-Kernighan's algorithm...
    	for (; number > 0; onesCount++, number &= (number - 1));
    	return onesCount;
    }
    
    public static long countBitFlipsRequiredToMatch(long a, long b) {
    	return a ^ b;
    }

    /**
     * Logic here is that - every bit flip at kth position of number results in 2^k possibilities
     * @param n
     * @return
     */
    public static long totalBitFlipsFor1ToN(long n) {
    	long radix2 = 1;
    	long bitFlipsCount = 1;
    	while (radix2 < n) {
    		bitFlipsCount += n / radix2;
    		// radix2 *= 2;
    		radix2 <<= 1;
    	}
        return bitFlipsCount;
    }

    public static void main(String[] args) {

    	System.out.println("One's set count");
    	System.out.println("---------------");
    	for (long num = 0 ; num < 100 ; num++) {
    		System.out.format("countOnesSetInNumber(%d)=%d%n", num, countOnesSetInNumber(num));
    	}

    	System.out.println();
    	System.out.println("Total bit flips");
    	System.out.println("---------------");
    	for (long num = 0 ; num < 100 ; num++) {
    		System.out.format("totalBitFlipsFor1ToN(%d)=%d%n", num, totalBitFlipsFor1ToN(num));
    	}
    }

}
