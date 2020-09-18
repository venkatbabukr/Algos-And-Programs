package com.venkat.algos.simple.arrays;

import java.util.Arrays;

/**
 * Leet code - https://leetcode.com/explore/learn/card/fun-with-arrays/525/inserting-items-into-an-array/3253/
 * 
 * @author vbkomarl
 */
public class MergeSortedArrays {

    /**
     * Merge nums1 and nums2 which are two sorted arrays, into nums1...
     * 
     * Assumption: nums1 has enough space to accommodate all m+n numbers...
     * 
     * @param nums1       First array having sorted numbers and into which nums2 will be merged
     * @param m           Number of elements in nums1
     * @param nums2       Second array having sorted numbers
     * @param n           Number of elements in nums2
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        m--;
        n--;
        int fillIdx = m + n + 1;
        while (m > -1 && n > -1) {
            while (m > -1 && nums1[m] >= nums2[n]) {
                nums1[fillIdx--] = nums1[m--];
            }
            nums1[fillIdx--] = nums2[n--];
        }
        // Blindly copy the remaining from nums2 into nums1 in the fillIdx because we know
        // we have exhausted all elements of num1 array...
        while (n > -1) {
            nums1[fillIdx--] = nums2[n--];
        }
        // No need of looping the remaining m..0 elements of nums1 as we are not copying them
        // into different array. If we had to copy into different array, the looping would be
        // required
        /*
        while (m > -1) {
            nums1[fillIdx--] = nums1[m--];
        }
        */
    }
    
    public static void main(String[] args) {
        int[] arr1 = new int[] {1, 2, 3, 0, 0, 0};
        int[] arr2 = new int[] {2, 5, 6};
        merge(arr1, 3, arr2, 3);
        System.out.format("Merged array: %s%n", Arrays.toString(arr1));
    }

}
