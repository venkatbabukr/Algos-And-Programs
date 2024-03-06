package com.venkat.algos.simple.sorting;

import java.util.Arrays;

/**
 * <p><b><u>Logic</u></b></p>
 * <p>Select ith minimum element in array and place it in ith's position</p>
 * <p><b><u>Best fit</u></b></p>
 * <p>Best suited for <b><i>Fully or almost fully sorted arrays</i></b>.</p>
 * <p><b><u>Other classifications</u></b></p>
 * <ul>
 *   <li>Internal sort (Array needs to be in memory)</li>
 *   <li>Stable sort (When elements are equal, the order from unsorted is maintained)</li>
 * </ul>
 * <p><b><u>Similar algorithms / Similarity</u></b></p>
 * <ul>
 *     <li>From space/time complexity &nbsp; comparisions/swaps perspective, this and {@link SelectionSort} are same.</li>
 *     <li>{@link InsertionSort} is better from the perspective of number of swaps in best to near-best cases</li>
 * </ul>
 * <p><b><u>Time & Space complexity analysis</u></b></p>
 * <table border="1">
 *     <tr>
 *         <td colspan="3"><b><i>Time complexity</i></b></td>
 *     </tr>
 *     <tr>
 *         <td>Best & Worst case time</td>
 *         <td><b>O(N^2)</b></td>
 *         <td><ul><li>The inner for loop executes for 1 + 2 + 3 + ... + (N-1) times</li><li>= ((N-1)*N)/2</li><li>= O(N^2)</li></td>
 *     </tr>
 *     <tr>
 *         <td colspan="3">&nbsp;</td>
 *     </tr>
 *     <tr>
 *         <td colspan="3"><b><i>Space complexity</i></b></td>
 *     </tr>
 *     <tr>
 *         <td>Best & Worst case space</td>
 *         <td><b>O(1)</b></td>
 *         <td>Doesn't take extra space</td>
 *     </tr>
 *     <tr>
 *         <td colspan="3">&nbsp;</td>
 *     </tr>
 *     <tr>
 *         <td colspan="3"><b><i># Comparisons</i></b></td>
 *     </tr>
 *     <tr>
 *         <td>Best & Worst case</td>
 *         <td><b>O(N^2)</b></td>
 *         <td>(N-1) + (N-2) + (N-3) + ... 1 comparisons made</td>
 *     </tr>
 *     <tr>
 *         <td colspan="3">&nbsp;</td>
 *     </tr>
 *     <tr>
 *         <td colspan="3"><b><i># Swaps</i></b></td>
 *     <tr>
 *         <td>Best case</td>
 *         <td><b>O(1)</b></td>
 *         <td><ul><li>Sorted array</li><li>Zero swaps</li></ul></td>
 *     </tr>
 *     <tr>
 *         <td>Worst case</td>
 *         <td><b>O(N^2)</b></td>
 *         <td>Condition: Completely unsorted array</td>
 *     </tr>
 * </table>
 * 
 * @author venkateshbabukr
 */
public class BubbleSort<T extends Comparable<T>> implements SortingAlgorithm<T> {

	@Override
	public void sort(T[] array) {
		for (int i = 0 ; i < array.length ; i++) {
			for (int j = array.length - 1 ; j > i ; j--) {
				if (array[j-1].compareTo(array[j]) > 0) {
					//i.e. if array[j-1] > array[j]
					T temp = array[j-1];
					array[j-1] = array[j];
					array[j] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {
		Integer[] arr = new Integer[] { 8, 6, 5, 9 , 3, 11 };
		System.out.format("Array: %s%n", Arrays.toString(arr));
		new BubbleSort<Integer>().sort(arr);
		System.out.format("Sorted array: %s%n", Arrays.toString(arr));
	}

}
