package com.venkat.algos.simple.arrays;

import java.util.Arrays;
import java.util.List;

import com.venkat.utils.Pair;
import com.venkat.utils.ext.ObjectsExt;

/**
 * https://www.geeksforgeeks.org/median-of-two-sorted-arrays-of-different-sizes/
 * 
 * @author vbkomarl
 */
public class TwoSortedArraysMedianSolver {

	/* O(M+N) solution */
    public static Double getMedian(Integer[] sortedArr1, Integer[] sortedArr2) {
        if (sortedArr1 == null || sortedArr2 == null)
            throw new IllegalArgumentException("Array parameter can't be null!");
        Integer combinedMedian = null;
        Double combinedMedianDouble = null;
        int combinedLen = sortedArr1.length + sortedArr2.length;
        int combinedMid = combinedLen / 2 + combinedLen % 2;
        int i = 0, j = 0, k = 0;
        Integer a1i, a2j;
        for ( ; k < combinedMid ; k++) {
        	a1i = i < sortedArr1.length ? sortedArr1[i] : null;
            a2j = j < sortedArr2.length ? sortedArr2[j] : null;
            combinedMedian = ObjectsExt.nullSafeMin(a1i, a2j);
            if (combinedMedian == a1i)
            	i++;
            else
            	j++;
        }
        if (combinedLen % 2 == 0) {
            a1i = i < sortedArr1.length ? sortedArr1[i] : null;
            a2j = j < sortedArr2.length ? sortedArr2[j] : null;
            combinedMedianDouble = (combinedMedian + ObjectsExt.nullSafeMin(a1i, a2j)) / 2.0;
        } else {
        	combinedMedianDouble = combinedMedian.doubleValue();
        }
        return combinedMedianDouble;
    }

    public static void main(String[] args) {
        List<Pair<Integer[]>> allMedianTestCases = Arrays.asList(
            new Pair<>(new Integer[] { 1, 3 }, new Integer[] { 2 }),
            new Pair<>(new Integer[] { 1, 2 }, new Integer[] { 3, 4 })
        );
        for (Pair<Integer[]> testCase : allMedianTestCases) {
            Integer[] arr1 = testCase.getX();
            Integer[] arr2 = testCase.getY();
            System.out.format("getMedian(%s, %s) = %f%n", Arrays.toString(arr1), Arrays.toString(arr2), getMedian(arr1, arr2));
        }
    }

}
