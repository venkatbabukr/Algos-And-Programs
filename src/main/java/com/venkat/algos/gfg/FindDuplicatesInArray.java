package com.venkat.algos.gfg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * The crux here is to find duplicates by doing an inline count and substitution.
 * Input array will be of size n having numbers from 1 to n.
 */
public class FindDuplicatesInArray {

	public static List<Integer> findDuplicates(int[] arr, int n) {
		int[] arrCopy = Arrays.copyOf(arr, arr.length);
        for (int i = 0 ; i < arrCopy.length ; i++) {
        	arrCopy[arrCopy[i] % n] += n;
        }
        List<Integer> duplicateNumbers = new ArrayList<>();
        for (int i = 0 ; i < arrCopy.length ; i++) {
            if (arrCopy[i] / n > 1) {
                duplicateNumbers.add(i);
            }
        }
        if (duplicateNumbers.size() <= 0) {
            duplicateNumbers.add(-1);
        }
        return duplicateNumbers;
	}
}
