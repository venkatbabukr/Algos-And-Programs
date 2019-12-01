package com.venkat.algos.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.function.Supplier;

public class SubSetSumSolver {
    
    /*
     * For now, nulls need not be handled, because all the public methods inside call Arrays functions
     * that are handling null value checks...
     */
    private static final class SubsetSum implements Supplier<int[]> {
        int[] sumArray;
        
        public SubsetSum(int[] arr) {
            sumArray = arr;
        }

        @Override
        public int[] get() {
            return sumArray;
        }
        
        public int hashCode() {
            return Arrays.hashCode(sumArray);
        }
        
        public boolean equals(Object other) {
            if (other != null && other instanceof SubsetSum) {
                return(Arrays.equals(this.sumArray, ((SubsetSum) other).sumArray));
            }
            return false;
        }
        
        public String toString() {
            return Arrays.toString(sumArray);
        }
        
    }
    
    private void validateInputs(int sum, int[] inputArr) {
        if (sum < 0) {
            throw new IllegalArgumentException("Sum can't be negative!");
        }
        if (inputArr == null) {
            throw new IllegalArgumentException("Input array can't be null");
        }
        if (Arrays.stream(inputArr).anyMatch(num -> num < 0)) {
            throw new IllegalArgumentException("Input array can't contain negative numbers!");
        }
    }
    
    private int[] sliceArray(int[] arr, int pos) {
        int[] subArray = new int[arr.length - 1];
        System.arraycopy(arr, 0, subArray, 0, pos);
        System.arraycopy(arr, pos + 1, subArray, pos, arr.length - pos - 1);
        return subArray;
    }

    public Set<SubsetSum> getAllSubsetsForSum(int sum, int[] inputArr) {
        validateInputs(sum, inputArr);

        Set<SubsetSum> matchingSubsets = new HashSet<>();
        Queue<int[]> searchQueue = new LinkedList<>();
        searchQueue.add(inputArr);

        while (!searchQueue.isEmpty()) {
            int[] arr = searchQueue.remove();
            int arrSum = Arrays.stream(arr).sum();
            if (arrSum == sum) {
                matchingSubsets.add(new SubsetSum(arr));
            } else if (arrSum > sum) {
                // Build tree only if the parent array sum is still greater than sum...
                for (int i = 0 ; i < arr.length ; i++) {
                    // This is the Pruning / Bounding condition... to reduce the search space...
                    if (arrSum - arr[i] == sum) {
                        matchingSubsets.add(new SubsetSum(sliceArray(arr, i)));
                    } else if (arrSum - arr[i] > sum) {
                        searchQueue.add(sliceArray(arr, i));
                    }
                }
            }
        }
        return matchingSubsets;
    }

    public static void main(String[] args) {
        int[] arr = null;
        int reqdSum = 0;
        Set<SubsetSum> subsetSums = null;
        SubSetSumSolver solver = new SubSetSumSolver();

        arr = new int[] {2, 3, 5, 8};
        reqdSum = 10;
        subsetSums = solver.getAllSubsetsForSum(reqdSum, arr);
        System.out.format("Subset sums for array %s and sum %d are %s\n",
                             Arrays.toString(arr),
                             reqdSum,
                             subsetSums);
        
        reqdSum = 8;
        subsetSums = solver.getAllSubsetsForSum(reqdSum, arr);
        System.out.format("Subset sums for array %s and sum %d are %s\n",
                             Arrays.toString(arr),
                             reqdSum,
                             subsetSums);
    }

}
