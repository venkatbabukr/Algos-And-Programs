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
     * O(nlogn) algo from wikipedia - https://en.wikipedia.org/wiki/Longest_increasing_subsequence

     * @return The longest increasing subsequence of given array
     */
    public Integer[] findLIS2() {
        int[] P = new int[arr.length];
        int[] isArr = new int[arr.length + 1];

        int maxSeqLen = 0;
        for (int i = 0; i < arr.length; i++) {
            // Binary search for the largest positive j <= L
            // such that X[M[j]] <= X[i]
            int lo = 1;
            int hi = maxSeqLen;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (arr[isArr[mid]] < arr[i])
                    lo = mid + 1;
                else
                    hi = mid - 1;
            }

            // After searching, lo is 1 greater than the
            // length of the longest prefix of X[i]

            // The predecessor of X[i] is the last index of
            // the subsequence of length newL-1
            P[i] = isArr[lo - 1];
            isArr[lo] = i;

            if (lo > maxSeqLen)
                // If we found a subsequence longer than any we've
                // found yet, update L
                maxSeqLen = lo;
        }

        // Reconstruct the longest increasing subsequence
        Integer[] lisArr = new Integer[maxSeqLen];
        int k = isArr[maxSeqLen];
        int fillIdx = maxSeqLen - 1;
        for (; fillIdx >= 0; fillIdx--, k = P[k]) {
            lisArr[fillIdx] = arr[k];
        }

        return lisArr;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] { 3, 10, 2, 1, 20 };
        Integer[] arr2 = new Integer[] { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
        LISSolver solver = new LISSolver(arr);
        System.out.format("LIS for %s is %s\n", Arrays.toString(arr), Arrays.toString(solver.findLIS()));
        solver = new LISSolver(arr2);
        System.out.format("LIS2 for %s is %s\n", Arrays.toString(arr2), Arrays.toString(solver.findLIS2()));
    }

}
