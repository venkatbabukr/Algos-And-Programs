package com.venkat.algos.simple.sorting;

import java.util.Arrays;

/**
 * <p><b><u>Logic</u></b></p>
 * <p>The crux of algorithm lies in being able to heapify element at any index in the array See {@link HeapSort#heapifyIdx(int[], int, int)}</p>
 * <p>The algorithm thus runs in 2 steps</p>
 * <p>
 * <ul>
 *   <li>
 *     Heapify (Create max heap) the complete array by heapifying the first N/2 elements (Non-leaf nodes)
 *   </li>
 *   <li> Run sortIndex from end of array &nbsp;
 *       <ul>
 *         <li>Swap max of heap with the sortIndex running from end of the array</li>
 *         <li>Heapify the array from 0 to sortIndex - 1</li>
 *       </ul>
 *   </li>
 * </ul>
 * </p>
 * <p><b><u>Best fit</u></b></p>
 * <p>Suited for any kinds of arrays. The runtime complexity is always O(NlogN)</p>
 * <p><b><u>Other classifications</u></b></p>
 * <ul>
 *   <li>Internal sort (Array needs to be in memory)</li>
 *   <li>Not a stable sort (When elements are equal, the order from unsorted is maintained)</li>
 * </ul>
 * <p><b><u>Similar algorithms / Similarity</u></b></p>
 * <ul>
 *     <li>{@link MergeSort} is having almost same performance. However {@link MergeSort} takes more space.</li>
 *     <li>{@link QuickSort} is closest with near O(NlogN) complexity depending on how we partition the array</li>
 * </ul>
 * <p><b><u>Time & Space complexity analysis</u></b></p>
 * <table border="1">
 *     <tr>
 *         <td colspan="3"><b><i>Time complexity</i></b></td>
 *     </tr>
 *     <tr>
 *         <td>Best & Worst case time</td>
 *         <td><b>O(NlogN)</b></td>
 *         <td>Heapify is always a logN time operation, and it is performed N/2 (Heapify array) + (N-1) times (Sorting time) at max</td>
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
 *         <td><b>O(NlogN)</b></td>
 *         <td>Best case - reduced comparisons is when the input array is already heapified</td>
 *     </tr>
 *     <tr>
 *         <td colspan="3">&nbsp;</td>
 *     </tr>
 *     <tr>
 *         <td colspan="3"><b><i># Swaps</i></b></td>
 *     <tr>
 *         <td>Best & Worst case</td>
 *         <td><b>O(NlogN)</b></td>
 *         <td>Best case - reduced swaps when the input array is already heapified</td>
 *     </tr>
 * </table>
 * 
 * @author venkateshbabukr
 */
public class HeapSort {

	/**
	 * Heapify the node at ith position in array where heap size is N. This is the crux of creating heap as well as sort.
	 * 
	 * @param array		Complete array
	 * @param idx		Index of node that needs to be heapified
	 * @param N			Size of heap
	 */
	public void heapifyIdx(int[] array, int idx, int N) {
		int nodeValue = array[idx];
		for (int childIdx = 2 * idx + 1 ; idx < N/2; idx = childIdx, childIdx = 2 * idx + 1) {
			if (childIdx < N - 1 && array[childIdx + 1] > array[childIdx]) {
				childIdx++;
			}
			if (nodeValue < array[childIdx]) {
				array[idx] = array[childIdx];
			} else {
				break;
			}
		}
		array[idx] = nodeValue;
	}

	public void heapifyArray(int[] array) {
		int N = array.length;
		// We need to iterate from N/2 - 1 to 0th element because all elements from N/2 to N are leaf nodes...
		// We need to heapify only non-leaf nodes
		for (int idx = N/2 ; idx >= 0 ; idx--) {
			heapifyIdx(array, idx, N);
		}
	}

	public void sort(int[] array) {
		// First heapify array
		heapifyArray(array);
		for (int sortIdx = array.length - 1 ; sortIdx > 0 ; sortIdx--) {
			swap(array, 0, sortIdx);
			heapifyIdx(array, 0, sortIdx - 1);
		}
	}

	private void swap(int[] array, int i, int j) {
		array[i] += array[j];
		array[j] = array[i] - array[j];
		array[i] = array[i] - array[j];
	}

	public static void main(String[] args) {
		HeapSort sorter = new HeapSort();
		
		int[] array = new int[] { 41, 26, 31, 97, 59, 53, 58 };
		System.out.format("Before heapify: %s%n", Arrays.toString(array));
		sorter.heapifyArray(array);
		System.out.format("After heapify: %s%n", Arrays.toString(array));
		sorter.sort(array);
		System.out.format("After sort: %s%n", Arrays.toString(array));
	}

}
