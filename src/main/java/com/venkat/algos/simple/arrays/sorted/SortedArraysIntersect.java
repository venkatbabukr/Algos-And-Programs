package com.venkat.algos.simple.arrays.sorted;

import com.venkat.algos.simple.MathExt;
import com.venkat.utils.ArraysExt;

public class SortedArraysIntersect {

    public int[] findIntersection(int[] sortedArray1, int[] sortedArray2) {
        int[] intersectArray = null;
        if (!ArraysExt.isEmpty(sortedArray1) && !ArraysExt.isEmpty(sortedArray2)) {
            intersectArray = new int[Math.min(sortedArray1.length, sortedArray2.length)];
            for (int fill = 0, i = 0, j = 0; i < sortedArray1.length && j < sortedArray2.length;) {
                if (sortedArray1[i] == sortedArray2[j]) {
                	intersectArray[fill] = sortedArray1[i];
                	fill++;i++;j++;
                } else if (sortedArray1[i] < sortedArray2[j])
                	i++;
                else
                	j++;
            }
        }
        return intersectArray;
    }

    public int[] findIntersection(int[] sortedArray1, int[] sortedArray2, int[] sortedArray3) {
        int[] intersectArray = null;
        if (!ArraysExt.isEmpty(sortedArray1) &&
            !ArraysExt.isEmpty(sortedArray2) &&
            !ArraysExt.isEmpty(sortedArray3)) {
            intersectArray = new int[MathExt.min(sortedArray1.length, sortedArray2.length, sortedArray3.length)];
            for (int fill = 0, i = 0, j = 0, k = 0;
                 i < sortedArray1.length && j < sortedArray2.length && k < sortedArray3.length;) {
                if (sortedArray1[i] == sortedArray2[j] && sortedArray2[j] == sortedArray3[k]) {
                	intersectArray[fill] = sortedArray1[i];
                	fill++;i++;j++;k++;
                } else if (sortedArray1[i] < sortedArray2[j])
                	i++;
                else if (sortedArray2[j] < sortedArray3[k])
                	j++;
                else
                	k++;
            }
        }
        return intersectArray;
    }

}
