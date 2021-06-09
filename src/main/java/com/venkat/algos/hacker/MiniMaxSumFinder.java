package com.venkat.algos.hacker;

import java.util.Arrays;

import com.venkat.utils.Pair;

/**
 * Solution for Hacker rank problem -
 * https://www.hackerrank.com/challenges/mini-max-sum/problem
 * 
 * Crux of algo - Use the fact 4 out of 5 numbers in array need to be summed!
 * Therefore, max sum = Array sum - min val, min sum = Array sum - max val
 */
public class MiniMaxSumFinder {
	
	public Pair<Integer> miniMaxSum(int[] arr) {
		int min, max, sum;
		max = sum = 0;
		min = Integer.MAX_VALUE;
		for (int i = 0 ; i < 5 ; i++) {
			sum += arr[i];
			if (arr[i] > max) max = arr[i];
			else if (arr[i] < min) min = arr[i];
		}
		return new Pair<Integer>(sum - max, sum - min);
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] {7, 69, 2, 221, 8974};
		MiniMaxSumFinder finder = new MiniMaxSumFinder();
		System.out.format("Min max result of arr - %s: %s%n", Arrays.toString(arr), finder.miniMaxSum(arr));
	}

}
