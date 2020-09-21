package com.venkat.algos.simple;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class MathExtTest {
    
    private static int findNumDigits(int num) {
        int numForCalc = Math.abs(num);
        int numDigits = 0;
        for(numDigits = 0 ; numForCalc > 0 ; numForCalc /= 10, numDigits++);
        return num == 0 ? 1 : numDigits;
    }

    @Test
    public void testFindNumDigits() {

        // Check for all positive numbers...
        Random randomGenerator = new Random();
        int num = 0;
        for (int radix = 0 ; radix < 10 ; radix++) {
            assertEquals("Mismatch occured for " + num, findNumDigits(num), MathExt.findNumDigits(num));
            num = num * 10 + randomGenerator.nextInt(10);
        }
        
        // Check for all negative numbers...
        num = 0;
        for (int radix = 0 ; radix < 10 ; radix++) {
            // System.out.format("Checking for %d%n", -1 * num);
            assertEquals("Mismatch occured for " + num, findNumDigits(-1 * num), MathExt.findNumDigits(-1 * num));
            num = num * 10 + randomGenerator.nextInt(10);
        }
    }
    
}
