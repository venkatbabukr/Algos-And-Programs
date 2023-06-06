package com.venkat.algos.gfg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://practice.geeksforgeeks.org/problems/subarray-with-given-sum-1587115621/1?page=1&category[]=Arrays&sortBy=submissions
 * 
 * @author venkateshbabukr
 *
 */
public class SubarraySum {

	static List<Integer> subarraySum(int[] arr, int n, int s) {
        int i, sum, sSubArrayStart, sSubArrayEnd;
        for (i = 0, sum = 0, sSubArrayStart = 0, sSubArrayEnd = 0; i < n && sum < s ; i++) {
        	sSubArrayEnd = i;
            sum += arr[sSubArrayEnd];

			while (sum > s && sSubArrayStart < sSubArrayEnd) {
				sum -= arr[sSubArrayStart];
				sSubArrayStart++;
			}
			if (sum > s) {
				sSubArrayEnd = sSubArrayStart = i + 1;
				sum = 0;
			}
		}
		ArrayList<Integer> subarraySumIdxs = new ArrayList<>();
		if (sum != s || s <= 0) {
		    subarraySumIdxs.add(-1);
		} else {
		    subarraySumIdxs.add(sSubArrayStart + 1);
		    subarraySumIdxs.add(sSubArrayEnd + 1);
		}
		return subarraySumIdxs;
	}
	
	public static void main(String[] args) {
		int[] testArr = new int[] { 1, 2, 3, 7, 5 };
		int n = 5, s = 12;

		System.out.format("subarraySum(%s, %d, %d)=%s%n,", Arrays.toString(testArr), n, s, subarraySum(testArr, n, s));

	}

}
