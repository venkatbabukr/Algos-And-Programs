package com.venkat.algos.greedy;

import java.util.Arrays;
import java.util.List;

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
            if (arr[i].equals(arr[majorCandidateIdx])) {
                majorCandidateCount++;
            } else {
                majorCandidateCount--;
                if (majorCandidateCount == 0) {
                    majorCandidateIdx = i;
                    majorCandidateCount = 1;
                }
            }
        }
        majorCandidateCount = 0;
        for (T val : arr) {
        	if (val.equals(arr[majorCandidateIdx])) {
        		majorCandidateCount++;
        	}
        }
        return majorCandidateCount >= arr.length / 2 ? arr[majorCandidateIdx] : null;
    }

    public static void main(String[] args) {
        List<Integer[]> testArrays = Arrays.asList(
                                         new Integer[] {2, 1, 2},
                                         new Integer[] {2, 2, 1, 1, 1},
                                         new Integer[] {2, 2, 1, 3, 5});
        MajorityElementsSolver<Integer> solver = new MajorityElementsSolver<>();
        for (Integer[] arr : testArrays)
            System.out.format("Majority element in %s is %s%n", Arrays.toString(arr), solver.findMajorityIn(arr));
    }

}
