package com.venkat.algos.simple.arrays;

import java.util.Arrays;

public class Dutch012Sorter {
	
	public static int[] sort012(int[] arr) {
		int[] resultArr = arr;
		if (arr != null) {
			resultArr = Arrays.copyOf(arr, arr.length);
			int leftmostZeroInsertIdx, mid, rightmostTwoInsertIdx, swapTemp;
			leftmostZeroInsertIdx = mid = 0;
			rightmostTwoInsertIdx = resultArr.length - 1;
			while (mid <= rightmostTwoInsertIdx) {
				switch (resultArr[mid]) {
					case 0:
						swapTemp = arr[leftmostZeroInsertIdx];
						arr[leftmostZeroInsertIdx] = arr[mid];
						arr[mid] = swapTemp;
						leftmostZeroInsertIdx++;
						mid++;
						break;
					case 1:
						mid++;
						break;
					case 2:
						swapTemp = arr[rightmostTwoInsertIdx];
						arr[rightmostTwoInsertIdx] = arr[mid];
						arr[mid] = swapTemp;
						rightmostTwoInsertIdx--;
						break;
				}
			}
		}
		return resultArr;
	}
	
	public static void main(String[] args) {
		int[][] testArrs = new int[][] {
			new int[] { 0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1 }
		};
		
		for (int[] testArr : testArrs) {
			System.out.format("sort012(%s)=%s", Arrays.toString(testArr), Arrays.toString(sort012(testArr)));
		}
	}

}
