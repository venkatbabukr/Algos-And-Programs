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
		int lsBegin, lsEnd, lsLen, begin, end;
		for (begin = lsBegin = lsLen = 0, end = lsEnd = 1; end < arr.length; end++) {
			if (arr[end] - arr[end - 1] > diff) {
				if (end - begin > lsLen) {
					lsBegin = begin;
					lsEnd = end;
					lsLen = lsEnd - lsBegin;
				}
				begin = end;
			}
		}
		return null;
	}

	// Not yet complete...
}
