package com.venkat.algos.simple.sorting;

import java.util.Arrays;

/**
 * <p><b><u>Logic</u></b></p>
 * <p>Insert every element at the appropriate sorted position of array. Given that first element of array is sorted,
 * keep forming the sub arrays by inserting rest of the elements at appropriate positions by comparing the
 * sorted sub-portion of array</p>
 * <p><b><u>Best fit</u></b></p>
 * <p>Best suited for <b><i>partially or fully sorted arrays</i></b>.</p>
 * <p><b><u>Other classifications</u></b></p>
 * <ul>
 *   <li>Internal sort (Array needs to be in memory)</li>
 *   <li>Stable sort (When elements are equal, the order from unsorted is maintained)</li>
 * </ul>
 * <p><b><u>Similar algorithms / Similarity</u></b></p>
 * <ul>
 *     <li>Best algorithm in case of sorted arrays!</li>
 *     <li>Better than both {@link BubbleSort} &nbsp; {@link SelectionSort} from the perspective of number of swaps in best to near-best cases</li>
 * </ul>
 * <p><b><u>Time & Space complexity analysis</u></b></p>
 * <table border="1">
 *     <tr>
 *         <td colspan="3"><b><i>Time complexity</i></b></td>
 *     </tr>
 *     <tr>
 *         <td>Best case time</td>
 *         <td><b>O(N)</b></td>
 *         <td>Condition: Sorted array</td>
 *     </tr>
 *     <tr>
 *         <td>Worst case time</td>
 *         <td><b>O(N^2)</b></td>
 *         <td>Condition: Completely unsorted array</td>
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
 *         <td>Best case</td>
 *         <td><b>O(N)</b></td>
 *         <td>Condition: Sorted array</td>
 *     </tr>
 *     <tr>
 *         <td>Worst case</td>
 *         <td><b>O(N^2)</b></td>
 *         <td>Condition: Completely unsorted array</td>
 *     </tr>
 *     <tr>
 *         <td colspan="3">&nbsp;</td>
 *     </tr>
 *     <tr>
 *         <td colspan="3"><b><i># Swaps</i></b></td>
 *     <tr>
 *         <td colspan="3">
 *             <p>The number of swaps in insertion sort = Number of inversions in an array because
 *             each element is swapped with exactly p elements who are greater than that number.</p>
 *             <p>Also, there is a theorem proved: The average number of inversions in an array of n
 *             distinct numbers is n*(n-1)/4</p>
 *         </td>
 *     </tr>
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
public class InsertionSort<T extends Comparable<T>> implements SortingAlgorithm<T> {

	public void sort(T[] array) {
		for (int i = 1 ; i < array.length ; i++) {
			T insertElement = array[i];
			int j = i - 1;
			for ( ; j >= 0 && array[j].compareTo(insertElement) > 0 ; j--) {
				array[j + 1] = array[j];
			}
			array[j + 1] = insertElement;
		}
	}
	
	public static void main(String[] args) {
		Integer[] arr = new Integer[] { 8, 6, 5, 9 , 3, 11 };
		System.out.format("Array: %s%n", Arrays.toString(arr));
		new InsertionSort<Integer>().sort(arr);
		System.out.format("Sorted array: %s%n", Arrays.toString(arr));
	}

}
