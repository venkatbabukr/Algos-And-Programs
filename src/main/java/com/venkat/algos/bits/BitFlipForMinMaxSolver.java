package com.venkat.algos.bits;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Problem is to find the minimum flips required in a given binary string to match it to All Zeros(0000...) or All Ones(1111...).
 * <br><br>
 * Constraint being that: When a bit at a particular position is flipped, all bits that are following/or to the right of that bit
 * are flipped...
 * <br><br>
 * Examples:
 *     1. 0111 => [0000]<br>
 *     2. 1010 => [1101, 1110, 1111]
 * <br><br>
 * The logic for solution is - to find first bit - whether it is '0' or '1', if '0' -> Flip remaining to match to all Zeros (000...)
 * otherwise, if '1' -> Flip remaining to match to all Ones (111...)
 * 
 * @author venkat
 */
public class BitFlipForMinMaxSolver {
    
    private String bitStr;
    
    public BitFlipForMinMaxSolver(String bitStr) {
        BitUtils.validateBitStr(bitStr);
        this.bitStr = bitStr;
    }

    /**
     * Utility function that flips all bits starting from startPos...
     * 
     * @param bitStrArray    The bits array
     * @param startPos       Position from where bits have to be flipped. All the bits from startPos to bitStrArray.length
     *                       are flipped inline (bitStrArray is modified)
     */
    private void flipBitsFrom(char[] bitStrArray, int startPos) {
        IntStream.range(startPos, bitStrArray.length)
            .forEach(pos -> BitUtils.flipBitAtPos(bitStrArray, pos));
    }

    /**
     * Returns list of all binary strings that were created by flipping bits to match given bitStr to All Zeros or All Ones...
     * <br><br>
     * Logic: Find out the bit to retain
     *                 char bitToRetain = bitStrArray[0];
     * <br><br>
     * Iterate rest of the bitStrArr and flip the bit at pos if it doesn't match bitToRetain
     * 
     * @return {@link List} of all binary strings created by flipping bits to match closes All Zeros/All Ones...
     */
    public List<String> getAllFlips() {
        List<String> bitFlipSequence = new ArrayList<>(bitStr.length() - 1);
        char[] bitStrArray = bitStr.toCharArray();
        char bitToRetain = bitStrArray[0];
        /*
         * Imp note: Donot use iterators() or streams here... because we want to manipulate the bitStrArray as we are
         * iterating over it...
         */
        for (int i = 1 ; i < bitStrArray.length ; i++) {
            if (bitStrArray[i] != bitToRetain) {
                flipBitsFrom(bitStrArray, i);
                bitFlipSequence.add(new String(bitStrArray));
            }
        }
        return bitFlipSequence;
    }

    public static void main(String[] args) {
        int numBits = 4;
        for (int i = 0 ; i < (1 << numBits) ; i++) {
            String bitStr = BitUtils.toBinaryString(i, numBits);
            System.out.format("%s: %s\n", bitStr, new BitFlipForMinMaxSolver(bitStr).getAllFlips());
        }
    }

}
