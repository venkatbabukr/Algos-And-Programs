package com.venkat.algos.simple.arrays;

import java.util.Arrays;

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

    public int[] getNumbersList() {
    	return numbersList;
    }

    public DutchOddEvenPartitioner partition() {
        int leftMostOdd = 0;
        int rightMostEven = numbersList.length - 1;
        while (leftMostOdd < rightMostEven) {
            while (numbersList[leftMostOdd] % 2 != 0 && leftMostOdd < rightMostEven)
                leftMostOdd++;
            while (numbersList[rightMostEven] % 2 == 0 && rightMostEven > leftMostOdd)
                rightMostEven--;
            if (leftMostOdd < rightMostEven) {
                swapNumbersInList(leftMostOdd, rightMostEven);
                leftMostOdd++;
                rightMostEven--;
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
    }

}
