package com.venkat.algos.simple.arrays;

import java.util.Arrays;
import java.util.List;

import com.venkat.algos.simple.MathExt;
import com.venkat.utils.Pair;

public class MaxSumSubSequenceSolver {

    protected int[] arr;

    protected int maxSum;
    
    protected Pair<Integer> maxSSIndices;

    public MaxSumSubSequenceSolver(int[] arrIp) {
        if (arrIp == null)
            throw new IllegalArgumentException("Array input can't be null!");
        this.arr = arrIp;
        computeMaxSumSubSeq();
    }

    public int computeMaxSubSum() {
        int sum = 0, maxSum = 0;
        for (int x : arr) {
            sum += x;
            maxSum = MathExt.max(sum, maxSum, x);
            if (sum < 0) sum = 0;
        }
        return maxSum;
    }

    protected void computeMaxSumSubSeq() {
        int sum = arr[0], currMaxSum = arr[0], currMaxBegin = 0, currMaxEnd = 0, 
            maxSum = arr[0], mssBegin = 0, mssEnd = 0;
        for (int i = 1 ; i < arr.length ; i++) {
            sum += arr[i];
            if (sum >= currMaxSum) {
                currMaxSum = sum;
                currMaxEnd = i;
            } else if (sum < 0) {
                for (; i < arr.length; i++) {
                    sum = arr[i];
                    if (sum > currMaxSum) {
                        currMaxSum = sum;
                        currMaxBegin = currMaxEnd = i;
                    }
                    if (arr[i] > 0 /* currSum > 0 */) break;
                }
            }
            if (currMaxSum > maxSum) {
                mssBegin = currMaxBegin;
                mssEnd = currMaxEnd;
                maxSum = currMaxSum;
            }
        }
        this.maxSum = maxSum;
        this.maxSSIndices = new Pair<>(mssBegin, mssEnd);
    }

    public int getMaxSum() {
        return this.maxSum;
    }

    public Pair<Integer> getMaxSSIndices() {
        return this.maxSSIndices;
    }

    public static void main(String[] args) {
        List<int[]> testArrs = Arrays.asList(
                                   new int[] { -1, -2, 0 },
                                   new int[] { 0, -4, 0, -8 },
                                   new int[] { 6, -3, -10, 0, 2 },
                                   new int[] { -1, -3, -10, 0, 60 },
                                   new int[] { -2, -40, 0, -2, -3 },
                                   new int[] { 0, -3, 4, -10, -1, -6, 0, 8, -8, -6, -5, -5, 0, -3, -9, 1, 5, -8, 0, 6, 1, -6, -8, 3, 0, -8, -9, 6, 8, 5 },
                                   new int[] { -2, -3, 4, -1, -2, 1, 5, -3 });

       for (int[] arr : testArrs) {
           MaxSumSubSequenceSolver solver = new MaxSumSubSequenceSolver(arr);
           System.out.format("Max sum %d (computed sum %d) subseq for arr %s = %s%n", solver.getMaxSum(), solver.computeMaxSubSum(), Arrays.toString(arr), solver.getMaxSSIndices());
       }
    }

}
