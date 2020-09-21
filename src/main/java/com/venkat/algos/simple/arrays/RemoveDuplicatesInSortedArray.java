package com.venkat.algos.simple.arrays;

import java.util.Arrays;

public class RemoveDuplicatesInSortedArray {

    public static int[] removeDupliates(int[] sortedNums) {
        int refillIdx = 0;
        for (int num : sortedNums) {
            if (sortedNums[refillIdx] != num)
                sortedNums[++refillIdx] = num;
        }
        for (int j = refillIdx + 1; j < sortedNums.length; j++)
            sortedNums[j] = 0;
        return sortedNums;
    }

    public static void main(String[] args) {
        int[] sortedNums = new int[] { 1, 1, 2 };
        System.out.format("%s after removing duplicates is %s",
                Arrays.toString(sortedNums),
                Arrays.toString(
                    removeDupliates(Arrays.copyOf(sortedNums, sortedNums.length))));
    }

}
