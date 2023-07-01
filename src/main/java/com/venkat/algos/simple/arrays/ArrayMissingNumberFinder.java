package com.venkat.algos.simple.arrays;

import java.util.Arrays;

public class ArrayMissingNumberFinder {

	/*
	 * The simplest and straight forward solution:
	 *    1. Find sum of all numbers of array.
     *    2. As we know only one number is missing, so, this sum will miss that number
     *    3. Therefore missing number = Sigma(N) = N(N+1)/2 - SUM(array)
	 */
	public static int findMissingNum(int[] arr, int N) {
		int sigmaN = (N * (N + 1)) / 2;
		int arraySum = Arrays.stream(arr).sum();
		return sigmaN - arraySum;
	}

	/*
	 * This is a simplified solution of above Missing num = N * (N + 1) / 2 - SUM(array).
	 * 
	 * Calculating N * (N + 1) / 2 mathematically may lead to integer overflow problem.
	 * If we reconvert the above mathematical equation to:
	 * 
	 * 1 - array[0] + 2 - array[1] + 3 - array[2] + ... + N - array[N-1], we get the same
	 * missing number required! This one will not cause overflow also...
	 */
	public static int findMissingNumOverflowSafe(int[] arr, int N) {
		int sum = 1;
		for (int i = 2 ; i < N + 1 ; i++) {
			sum += i - arr[i - 2];
		}
		return sum;
	}
	
	/*
	 * This solution uses following XOR property:
	 * Let a1 ^ a2 ^ a3 ^ ... aN = nXOR
	 *     a1 ^ a2 ^ a3 ^ ... aN-1 = nPrevXOR
	 *     
	 * Then, nPrevXOR ^ nXOR = n
	 */
	public static int findMissingNumUsingXOR(int[] arr, int N) {
		int nXOR = 1, arrXOR = 0;
		for (int i = 2 ; i < N + 1 ; i++) {
			nXOR ^= i;
			arrXOR ^= arr[i - 2];
		}
		return nXOR ^ arrXOR;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] { 1, 3 };
		System.out.format("Missing number in %s is %d%n", Arrays.toString(arr), findMissingNumOverflowSafe(arr, 3));
		
		int xor = 0;
		for (int i = 1 ; i <= 3 ; i++) {
			xor = xor ^ i;
			System.out.format("i=%d, xor=%d%n", i, xor);
		}
	}

}
