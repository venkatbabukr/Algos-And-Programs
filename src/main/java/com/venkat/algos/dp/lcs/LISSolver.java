package com.venkat.algos.dp.lcs;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class LISSolver {

    private Integer[] arr;

    private static final class LISInfoNode {
        private int seqLen;
        private int prev;

        public LISInfoNode() {
            this.seqLen = 0;
            this.prev = -1;
        }

        public String toString() {
            return String.format("{L: %d,P: %d}", seqLen, prev);
        }

    }

    public LISSolver(Integer[] inputArr) {
        Objects.requireNonNull(inputArr);
        if (inputArr.length == 0) {
            throw new IllegalArgumentException("Input array shouldn't be empty!");
        }
        this.arr = inputArr;
    }

    public Integer[] findLIS() {
        LISInfoNode[] lisInfoArr = new LISInfoNode[arr.length];

        for (int i = 0; i < arr.length; i++) {
            LISInfoNode lisAtI = new LISInfoNode();
            for (int j = 0; j < i; j++) {
                LISInfoNode lisAtJ = lisInfoArr[j];
                if (arr[j] <= arr[i] && lisAtI.seqLen < lisAtJ.seqLen + 1) {
                    lisAtI.seqLen = lisAtJ.seqLen + 1;
                    lisAtI.prev = j;
                }
            }
            lisInfoArr[i] = lisAtI;
        }

        int maxLisIdx = IntStream.range(0, lisInfoArr.length).reduce(0,
                (i, j) -> lisInfoArr[i].seqLen > lisInfoArr[j].seqLen ? i : j);

        Integer[] lisArray = new Integer[lisInfoArr[maxLisIdx].seqLen + 1];
        for (int fillIdx = lisArray.length - 1; fillIdx >= 0; fillIdx--, maxLisIdx = lisInfoArr[maxLisIdx].prev) {
            lisArray[fillIdx] = arr[maxLisIdx];
        }
        return lisArray;
    }

    /**
     * O(nlogn) algo
     * Multiple sources listed in order or ease of understanding
     * - https://www.interviewbit.com/blog/longest-increasing-subsequence/
     * - https://en.wikipedia.org/wiki/Longest_increasing_subsequence
     * 
     * @return The longest increasing subsequence of given array
     */
    public Integer[] findLIS2() {
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
        Integer[] arr = new Integer[] { 3, 10, 2, 1, 20 };
        LISSolver solver = new LISSolver(arr);
        System.out.format("LIS for %s is %s\n", Arrays.toString(arr), Arrays.toString(solver.findLIS()));

        arr = new Integer[] { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
        solver = new LISSolver(arr);
        System.out.format("LIS2 for %s is %s\n", Arrays.toString(arr), Arrays.toString(solver.findLIS2()));

        arr = new Integer[] {12, 16, 18, 13, 14, 15, 11};
        solver = new LISSolver(arr);
        System.out.format("LIS2 for %s is %s\n", Arrays.toString(arr), Arrays.toString(solver.findLIS2()));
    }

}
