package com.venkat.algos.simple.arrays;

import java.util.Arrays;

/**
 * Removes the given value from inside the array by shifting others to it's position...
 * 
 * Leetcode: https://leetcode.com/explore/learn/card/fun-with-arrays/526/deleting-items-from-an-array/3247/
 * 
 * @author vbkomarl
 *
 */
public class RemoveElementByValue {
    
    public static int[] removeAll(int[] arr, int valueToRemove) {
        int refillPos = 0;
        for (int num : arr) {
            if (num != valueToRemove)
                arr[refillPos++] = num;
        }
        for (int j = refillPos ; j < arr.length ; j++)
            arr[j] = 0;
        return arr;
    }
    
    public static void main(String[] args) {
        int[] testArr = new int[] { 3, 2, 2, 3 };
        int valueToRemove = 3;
        System.out.format("Given array: %s, after removal of %d: %s",
                              Arrays.toString(testArr),
                              valueToRemove,
                              Arrays.toString(removeAll(testArr, valueToRemove)));
    }

}
