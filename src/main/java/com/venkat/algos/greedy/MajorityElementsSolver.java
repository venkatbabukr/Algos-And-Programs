package com.venkat.algos.greedy;

import java.util.Arrays;

public class MajorityElementsSolver<T> {

    /**
     * Solution using Moore's voting algorithm. Source: https://www.geeksforgeeks.org/majority-element/
     * 
     * @param arr The array in which we need to find majority element
     * @return    Majority element in the array.
     */
    public T findMajorityIn(T[] arr) {
        int majorCandidateCount = 1, majorCandidateIdx = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].equals(arr[i - 1])) {
                majorCandidateCount++;
            } else {
                majorCandidateCount--;
                if (majorCandidateCount <= 0) {
                    majorCandidateIdx = i;
                    majorCandidateCount = 1;
                }
            }
        }
        return majorCandidateCount > 0 ? arr[majorCandidateIdx] : null;
    }

    public static void main(String[] args) {
        Integer[] arr1 = new Integer[] {2, 1, 2};
        MajorityElementsSolver<Integer> solver = new MajorityElementsSolver<>();
        System.out.format("Majority element in %s is %s", Arrays.toString(arr1), solver.findMajorityIn(arr1));
    }

}
