package com.venkat.algos.simple.sorting;

import java.util.Arrays;

/**
 * <p><b><u>Logic</u></b></p>
 * <p>Partition the array based on first element of the array such that left partition has all elements &lt;= first element
 * and right partition has all elements &gt; first element. Now sort these two partitions again until we reach array of size 1
 * </p>
 *
 * @author venkateshbabukr
 */
public class QuickSort<T extends Comparable<T>> implements SortingAlgorithm<T> {

	@Override
	public void sort(T[] array) {
		quickSort(array, 0, array.length - 1);
	}

	private void quickSort(T[] array, int low, int high) {
		int sortArraySize = (high - low) + 1;
		if (sortArraySize > 2) {
			int mid = (low + high) / 2;
			// Put the median of array[low], array[mid] and array[high] in array[mid]
			setMedianInMiddle(array, low, mid, high);
			if (sortArraySize > 3) {
				// Now pivot the median by placing it in array[high]
				swap(array, mid, high);
				// Partition the array on pivot
				int pl = low, ph = high - 1;
				while (pl < ph) {
					while (pl < ph && array[pl].compareTo(array[high]) < 0) pl++;
					while (pl < ph && array[ph].compareTo(array[high]) >= 0) ph--;
					if (pl < ph) {
						swap(array, pl, ph);
					}
				}
				swap(array, pl, high); // Pivot element currently stored in array[high] need to come to array[pl] it's original position.
				quickSort(array, low, pl - 1); // Quick sort the left partition
				quickSort(array, pl + 1, high); // Quick sort the right partition.
			}

			
		} else if (sortArraySize == 2 && array[low].compareTo(array[high]) > 0) {
			swap(array, low, high);
		}
	}

	private void setMedianInMiddle(T[] array, int low, int mid, int high) {
		if (array[low].compareTo(array[mid]) > 0) {
			swap(array, low, mid);
		}
		if (array[low].compareTo(array[high]) > 0) {
			swap(array, low, high);
		}
		if (array[mid].compareTo(array[high]) > 0) {
			swap(array, mid, high);
		}
		// The above three if conditions ensure invariance:
		// array[low] <= array[mid] <= array[high]
		// Therefore, array[mid] is the median!

	}

	private void swap(T[] array, int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void main(String[] args) {
		QuickSort<Integer> sorter = new QuickSort<>();
		Integer[] arr = null;

		arr = new Integer[] { 8, 6, 5, 9 , 3, 11 };
		System.out.format("Array: %s%n", Arrays.toString(arr));
		sorter.sort(arr);
		System.out.format("Sorted array: %s%n", Arrays.toString(arr));

		arr = new Integer[] { 8, 1, 4, 9, 0, 3, 5, 2, 7, 6 };
		System.out.format("Array: %s%n", Arrays.toString(arr));
		sorter.sort(arr);
		System.out.format("Sorted array: %s%n", Arrays.toString(arr));

		arr = new Integer[] { 8, 1, 4, 9, 0, 5, 3, 5, 2, 7, 6 };
		System.out.format("Array: %s%n", Arrays.toString(arr));
		sorter.sort(arr);
		System.out.format("Sorted array: %s%n", Arrays.toString(arr));
	}

}
