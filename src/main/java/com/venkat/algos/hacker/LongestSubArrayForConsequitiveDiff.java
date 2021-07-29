package com.venkat.algos.hacker;

import com.venkat.utils.Pair;

/**
 * Solution to hackerrank problem:
 * https://www.hackerrank.com/challenges/picking-numbers/problem
 * 
 * As you keep progressing, keep track of the sub array begin and end... If the numbers
 * diff exceeds given diff, reposition the sub array begin else keep incrementing
 * sub array end!
 * 
 * You'll have to keep track of longest sub array, so, keep two more pointers:
 * lsBegin and lsEnd...
 *
 */
public class LongestSubArrayForConsequitiveDiff {
	
	public Pair<Integer> findSubArray(int[] arr, int diff) {
		int lsBegin, lsEnd, sBegin, sEnd;
		for (sBegin = lsBegin = 0, sEnd = lsEnd = 1; sEnd < arr.length; sEnd++) {
			if (arr[sEnd] - arr[sEnd - 1] > diff) {
				if (sEnd - sBegin > lsEnd - lsBegin) {
					lsBegin = sBegin;
					lsEnd = sEnd;
				}
				sBegin = sEnd;
			}
		}
		if (sEnd - sBegin > lsEnd - lsBegin) {
			lsBegin = sBegin;
			lsEnd = sEnd;
		}
		return new Pair<>(lsBegin, lsEnd);
	}

	public static void main(String[] args) {
		int[] arr = new int[] {1, 1, 2, 2, 4, 4, 5, 5, 5};
		LongestSubArrayForConsequitiveDiff finder = new LongestSubArrayForConsequitiveDiff();
		System.out.println(finder.findSubArray(arr, 1));
	}

}
