package com.venkat.algos.hacker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Solution to the hacker rank Sub-array division problem: https://www.hackerrank.com/challenges/the-birthday-bar/problem
 * 
 * The crux of this algorithm is to perform only successive addition & subtraction. Once we have a block of size n (or len),
 * to get next block of this size, remove the left-most element in current block and add to the block new array element that is
 * on the right, and keep checking if your sum condition/criteria is satisfied...
 * 
 * @author vbkomarl
 */
public class SubArrayDivisionSum {

    public List<int[]> getSubArraysForSum(int[] arr, int subLen, int subSum) {
        List<int[]> subArrays = new ArrayList<>(0);
        if (subLen <= arr.length) {
            subArrays = new ArrayList<>(arr.length / subLen);
            // Calculate initial sum...
            int subBlockSum = 0;
            for (int i = 0 ; i < subLen; i++) {
                subBlockSum += arr[i];
            }
            if (subBlockSum == subSum) {
                subArrays.add(Arrays.copyOfRange(arr, 0, subLen));
            }
            
            // Perform just successive additions for remaining elements...
            for (int i = subLen, j = 0; i < arr.length; i++, j++) {
                subBlockSum -= arr[j];
                subBlockSum += arr[i];
                if (subBlockSum == subSum) {
                    subArrays.add(Arrays.copyOfRange(arr, j + 1, i + 1));
                }
            }
        }
        return subArrays;
    }
    
    public static void main(String[] args) {
        SubArrayDivisionSum solver = new SubArrayDivisionSum();

        int[] arr = new int[] { 2, 2, 1, 3, 2 };
        int subLen = 2;
        int subSum = 4;
        List<int[]> subArrs = solver.getSubArraysForSum(arr, subLen, subSum);
        System.out.format("Sub arrays of size %d with sum %d, for array %s are: %s%n", subLen, subSum,
                Arrays.toString(arr),
                subArrs.stream().map(subArr -> Arrays.toString(subArr)).collect(Collectors.joining(", ")));

        arr = new int[] { 1, 2, 1, 3, 2 };
        subLen = 2;
        subSum = 3;
        subArrs = solver.getSubArraysForSum(arr, subLen, subSum);
        System.out.format("Sub arrays of size %d with sum %d, for array %s are: %s%n", subLen, subSum,
                Arrays.toString(arr),
                subArrs.stream().map(subArr -> Arrays.toString(subArr)).collect(Collectors.joining(", ")));

        arr = new int[] { 1, 1, 1, 1, 1, 1 };
        subLen = 2;
        subSum = 3;
        subArrs = solver.getSubArraysForSum(arr, subLen, subSum);
        System.out.format("Sub arrays of size %d with sum %d, for array %s are: %s%n", subLen, subSum,
                Arrays.toString(arr),
                subArrs.stream().map(subArr -> Arrays.toString(subArr)).collect(Collectors.joining(", ")));
    }

}
