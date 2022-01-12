package com.venkat.algos.dp.seq;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LISSolver {

    private Integer[] arr;

    public LISSolver(Integer[] inputArr) {
        Objects.requireNonNull(inputArr);
        if (inputArr.length == 0) {
            throw new IllegalArgumentException("Input array shouldn't be empty!");
        }
        this.arr = inputArr;
    }

    /**
     * O(nlogn) algo
     * Multiple sources listed in order or ease of understanding
     * - https://www.interviewbit.com/blog/longest-increasing-subsequence/
     * - https://en.wikipedia.org/wiki/Longest_increasing_subsequence
     * 
     * @return The longest increasing subsequence of given array
     */
    public Integer[] findSingleLIS() {
        int[] tails = new int[arr.length + 1];
        int[] prevPtr = new int[arr.length];
        int longestTailSize = 0;
        for (int idx = 0 ; idx < arr.length; idx++) {
            int i = 1, j = longestTailSize;
            while (i <= j) {
                int mid = (i + j) / 2;
                if (arr[tails[mid]] < arr[idx]) {
                    i = mid + 1;
                } else {
                    j = mid - 1;
                }
            }
            prevPtr[idx] = tails[i - 1];
            tails[i] = idx;
            if (i > longestTailSize) {
                longestTailSize = i;
            }
        }
        // Reconstruct the longest increasing subsequence
        Integer[] lisArr = new Integer[longestTailSize];
        for (int k = longestTailSize - 1, sIdx = tails[longestTailSize]; k >= 0; k--, sIdx = prevPtr[sIdx]) {
            lisArr[k] = arr[sIdx];
        }

        return lisArr;
    }

    public static void main(String[] args) {
        List<Integer[]> allTestSequences = Arrays.asList(
                                               new Integer[] { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 },
                                               new Integer[] {12, 16, 18, 13, 14, 15, 11},
                                               new Integer[] { 69, 54, 19, 51, 16, 54, 64, 89, 72, 40, 31, 43, 1, 11, 82, 65, 75, 67, 25, 98, 31, 77, 55, 88, 85, 76, 35, 101, 44, 74, 29, 94, 72, 39, 20, 24, 23, 66, 16, 95, 5, 17, 54, 89, 93, 10, 7, 88, 68, 10, 11, 22, 25, 50, 18, 59, 79, 87, 7, 49, 26, 96, 27, 19, 67, 35, 50, 10, 6, 48, 38, 28, 66, 94, 60, 27, 76, 4, 43, 66, 14, 8, 78, 72, 21, 56, 34, 90, 89 });

        Integer[] testArr = new Integer[] { 3, 10, 2, 1, 20 };
        LISSolver solver = new LISSolver(testArr);
        System.out.format("LIS for %s is %s\n", Arrays.toString(testArr), Arrays.toString(solver.findSingleLIS()));

        for (Integer[] arr : allTestSequences) {
            solver = new LISSolver(arr);
            System.out.format("LIS2 for %s is %s\n", Arrays.toString(arr), Arrays.toString(solver.findSingleLIS()));
        }

    }

}
