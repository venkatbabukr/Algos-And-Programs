package com.venkat.algos.simple.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.venkat.utils.Pair;

public class ArraySumPairsFinder {

    static class TestCase {
        int[] arr;
        int sum;

        public TestCase(int[] a, int s) {
        	this.arr = a;
        	this.sum = s;
        }
    }

    public List<Pair<Integer>> findAllSumPairsInArr(int[] arr, int sum) {
        Set<Integer> complementaries = new HashSet<>();
        List<Pair<Integer>> sumPairs = new ArrayList<>(arr.length * 2);
        for (int x : arr) {
        	if (complementaries.contains(sum - x)) {
        		sumPairs.add(new Pair<>(sum - x, x));
        	}
            complementaries.add(x);
        }
        return sumPairs;
    }

    public static void main(String[] args) {
        TestCase[] allTests = new TestCase[] {
            new TestCase(new int[] {0, -1, 2, -3, 1}, -2),
            new TestCase(new int[] {1, -2, 1, 0, 5}, 0)
        };
        ArraySumPairsFinder finder = new ArraySumPairsFinder();

        for (TestCase t : allTests) {
            System.out.format("Array=%s, Sum=%s, All pairs=%s%n", Arrays.toString(t.arr),
                                  t.sum, finder.findAllSumPairsInArr(t.arr, t.sum));
        }
    }

}
