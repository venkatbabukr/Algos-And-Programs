package com.venkat.algos.simple.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class DutchOddEvenPartitioner {

    private int[] numbersList;

    public DutchOddEvenPartitioner(int[] numbers) {
        if (numbers == null)
            throw new IllegalArgumentException("numbers list can't be null!");
        this.numbersList = numbers;
    }

    private void swapNumbersInList(int pos1, int pos2) {
        int temp = numbersList[pos1];
        numbersList[pos1] = numbersList[pos2];
        numbersList[pos2] = temp;
    }

    private void bubbleNumber(int atPos, int toPos, Comparator<Integer> comparator) {
        int incr = atPos < toPos ? 1 : -1;
        while (atPos != toPos && comparator.compare(numbersList[atPos], numbersList[atPos + incr]) < 0) {
            swapNumbersInList(atPos, atPos + incr);
            atPos += incr;
        }
    }

    public int[] getNumbersList() {
    	return numbersList;
    }

    public DutchOddEvenPartitioner partition() {
        int leftMostEven = 0;
        int rightMostOdd = numbersList.length - 1;
        while (leftMostEven < rightMostOdd) {
            while (numbersList[leftMostEven] % 2 == 1 && leftMostEven < rightMostOdd)
                leftMostEven++;
            while (numbersList[rightMostOdd] % 2 == 0 && rightMostOdd > leftMostEven)
                rightMostOdd--;
            if (leftMostEven < rightMostOdd) {
                swapNumbersInList(leftMostEven, rightMostOdd);
                leftMostEven++;
                rightMostOdd--;
            }
        }
        return this;
    }

    /**
     * Incorporate partition logic along with simple bubbling function of bubble sort
     */
    public DutchOddEvenPartitioner partitionWithSort() {
        int leftMostEven = 0;
        int rightMostOdd = numbersList.length - 1;
        while (leftMostEven < rightMostOdd) {
            while (numbersList[leftMostEven] % 2 == 1 && leftMostEven < rightMostOdd) {
                bubbleNumber(leftMostEven, 0, Integer::compare);
                leftMostEven++;
            }
            while (numbersList[rightMostOdd] % 2 == 0 && rightMostOdd > leftMostEven) {
                bubbleNumber(rightMostOdd, numbersList.length - 1, Collections.reverseOrder(Integer::compare));
                rightMostOdd--;
            }
            if (leftMostEven < rightMostOdd) {
                swapNumbersInList(leftMostEven, rightMostOdd);
                // After swap, bubble numbers to the appropriate position
                // and then increment leftMostEven and decrement rightMostOdd
                bubbleNumber(leftMostEven, 0, Integer::compare);
                bubbleNumber(rightMostOdd, numbersList.length - 1, Collections.reverseOrder(Integer::compare));
                leftMostEven++;
                rightMostOdd--;
            }
        }
        return this;
    }

    public static void main(String[] args) {
        int arr[] = {12, 34, 45, 9, 8, 90, 3};
        System.out.format("Before partition: %s\n", Arrays.toString(arr));

        int[] arr1 = Arrays.copyOf(arr, arr.length);
        new DutchOddEvenPartitioner(arr1).partition();
        System.out.format("After partition: %s\n", Arrays.toString(arr1));

        arr1 = Arrays.copyOf(arr, arr.length);
        new DutchOddEvenPartitioner(arr1).partitionWithSort();
        System.out.format("After partition with sort: %s\n", Arrays.toString(arr1));
    }

}
