package com.venkat.algos.simple.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.venkat.utils.ext.ArraysExt;

public class ArrayAlgos {

    public static <T> List<T[]> getSubArrays(T[] arr, int size) {
        List<T[]> allCombos = null;
        if (!ArraysExt.isEmpty(arr) && size > 0) {
            allCombos = new ArrayList<>();
            for (int i = 0 ; i < arr.length ; i++) {
                T[] baseCombo = Arrays.copyOfRange(arr, i, i + size - 1);
                for (int j = i + size - 1; j < arr.length; j++) {
                    T[] combo = Arrays.copyOf(baseCombo, size);
                    combo[size - 1] = arr[j];
                    allCombos.add(combo);
                }
            }
        }
        return allCombos;
    }

    public static int equilibriumPoint(int[] arr) {
    	if (arr == null) return -1;
    	long arrSum = Arrays.stream(arr).sum();
    	long leftSum = 0;
    	for (int i = 0 ; i < arr.length ; i++) {
    		arrSum -= arr[i];
    		if (leftSum == arrSum)
    			return i;
    		leftSum += arr[i];
    	}
    	return -1;
    }

    public static <T, R> Map<T[], R> getAllSubArrays(T[] arr, Function<T[], R> subArrayProcessor) {
        if (!ArraysExt.isEmpty(arr)) {
            final Map<T[], R> allSubArrays = new LinkedHashMap<>();
            class SubArrayData {
                T[] arr;
                int lastElementIdx;
                
                SubArrayData(T[] sArr, int lIdx) {
                    this.arr = sArr;
                    this.lastElementIdx = lIdx;
                }
            };

            // Put all Size 1 sub arrays to begin with...
            List<SubArrayData> sizeISubArrays = IntStream.range(0, arr.length)
                                                    .boxed()
                                                    .map(i -> new SubArrayData(Arrays.copyOfRange(arr, i, i + 1) , i))
                                                    .collect(Collectors.toList());
            while (!sizeISubArrays.isEmpty()) {
                // Build sizeI + 1 sub arrays from sizeI sub array...
                List<SubArrayData> sizeIPlusOneSubArrays = sizeISubArrays
                                                               .stream()
                                                               // Apply the consumer here...
                                                               .peek(d -> allSubArrays.put(d.arr, subArrayProcessor.apply(d.arr)))
                                                               .filter(d -> d.lastElementIdx < arr.length - 1)
                                                               .map(d -> {
                                                                       T[] newSizePlusOneSubArr = Arrays.copyOf(d.arr, d.arr.length + 1);
                                                                       int newLastElementIdx = d.lastElementIdx + 1;
                                                                    newSizePlusOneSubArr[newSizePlusOneSubArr.length - 1] = arr[newLastElementIdx];
                                                                    return new SubArrayData(newSizePlusOneSubArr, newLastElementIdx);
                                                               })
                                                               .collect(Collectors.toList());
                sizeISubArrays = sizeIPlusOneSubArrays;
            }
            return allSubArrays;
        }
        return null;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] {1, 2, 3, 4, 5};
        int subArraySize = 2;
        System.out.format("Arr=%s, Subarrays of size %d=%s%n", Arrays.toString(arr), subArraySize,
                             getSubArrays(arr, subArraySize)
                                 .stream()
                                 .map(subArr -> Arrays.toString(subArr))
                                 .collect(Collectors.joining(", ")));

        System.out.format("Arr=%s, All Subarrays=%s%n", Arrays.toString(arr),
                             getAllSubArrays(arr, a -> a.length)
                                 .entrySet()
                                 .stream()
                                 .map(e -> String.format("Subarr=%s, Result=%d", Arrays.toString(e.getKey()), e.getValue()))
                                 .collect(Collectors.joining(", ")));
    }

}
