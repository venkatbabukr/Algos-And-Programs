package com.venkat.algos.hacker;

import java.util.Arrays;

/**
 * Solution to hacker rank problem - https://www.hackerrank.com/challenges/divisible-sum-pairs/problem
 * 
 * The problem can be solved in O(n) time with O(k) additional space utilization. Start from arr[1] and go till
 * arr[len]. In each step, increment arr[i] % k, i.e. mod(arr[i] % k)++ and check for the complement count existing
 * i.e. totalPairsPossible += mod((k - arr[i] % k) % k). The last % k is required for the case of arr[i] % k == 0
 * 
 * @author vbkomarl
 */
public class DivisibleSumPairs {

    // Solution from - https://chongtang.gitbooks.io/hackerrank/content/divisible_sum_pairs.html
    public int otherGetDivisibleSumPairsCount(int[] arr, int k) {
        int[] modCount = new int[k];
        for (int num : arr) {
            modCount[num % k]++;
        }
        // The modCount[0] numbers (i.e. numbers totally divisible by k) can form n * (n - 1) / 2
        // composite pairs themselves...
        int totalPossiblePairs = modCount[0] * (modCount[0] - 1) / 2;
        // For reminders from 1 to k-1, we compute the number of pairs they can composite.
        for (int i = 1 ; i < (k + 1) / 2 ; i++) {
            totalPossiblePairs += modCount[i] * modCount[k - i];
        }
        // At last, if k is an even number, for example 10, 
        // the above loop will miss the reminder in the middle, which is 5
        // we need to compute the number of pairs which in this set, similar to reminder 0
        if (k % 2 == 0) {
            totalPossiblePairs += modCount[k/2] * (modCount[k/2] - 1)/2;
        }
        return totalPossiblePairs;
    }

    public int getDivisibleSumPairsCount(int[] arr, int k) {
    	int[] nmodkCount = new int[k];
    	nmodkCount[arr[0] % k]++;
    	int totalPossiblePairs = 0;
    	for (int idx = 1 ; idx < arr.length ; idx++) {
    		int aimodk = arr[idx] % k;
    		totalPossiblePairs += nmodkCount[(k - aimodk) % k];
    		nmodkCount[aimodk]++;
    	}
    	return totalPossiblePairs;
    }

    public static void main(String[] args) {
        DivisibleSumPairs solver = new DivisibleSumPairs();
        int[] arr = new int[] {1, 3, 2, 6, 1, 2};
        int k = 3;
        System.out.format("Number of divisible pairs for arr=%s and k=%d are %d%n", Arrays.toString(arr), k,
                          solver.getDivisibleSumPairsCount(arr, k));
    }

}
