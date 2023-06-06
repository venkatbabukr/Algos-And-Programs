package com.venkat.algos.simple.arrays.matrix;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class ArrayComparisons {
	
	@Test
	public void test1() {
		Integer[][] arr1 = new Integer[][] {
			new Integer[] { 1, 2, 3},
			new Integer[] { 4, 5, 6}
		};
		Integer[][] arr2 = new Integer[][] {
			new Integer[] { 1, 2, 3},
			new Integer[] { 4, 5, 6}
		};
		assertArrayEquals(arr1, arr2);
	}

}
