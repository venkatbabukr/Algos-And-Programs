package com.venkat.algos.simple.arrays;

import java.util.Arrays;

/**
 * Leet code: https://leetcode.com/explore/learn/card/fun-with-arrays/521/introduction/3240/
 * 
 * @author vbkomarl
 */
public class SquareSortedArrays {

    public static int[] getSortedSquares(int[] sortedArray) {
        int[] squaresArray = new int[sortedArray.length];
        for (int fillIdx = squaresArray.length - 1,
                 lookupIdxMin = 0,
                 lookupIdxMax = sortedArray.length - 1; fillIdx >= 0; fillIdx--) {
            int idxToUse = -1;
            if (lookupIdxMin < lookupIdxMax && Math.abs(sortedArray[lookupIdxMin]) > Math.abs(sortedArray[lookupIdxMax])) {
                idxToUse = lookupIdxMin++;
            } else {
                idxToUse = lookupIdxMax--;
            }
            squaresArray[fillIdx] = sortedArray[idxToUse] * sortedArray[idxToUse];
        }
        return squaresArray;
    }
    
    public static void main(String[] args) {
        int[] sortedArray = new int[] {-4,-1,0,3,10};
        System.out.format("Sorted squares of array %s is %s%n",
            Arrays.toString(sortedArray),
            Arrays.toString(getSortedSquares(sortedArray)));
    }

}
