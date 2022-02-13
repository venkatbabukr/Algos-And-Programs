package com.venkat.algos.dp.matrix.more;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class LISSolverLegacy {

	private Integer[] arr;

    private static final class LISInfoNode {
        private int seqLen;
        private int prev;

        public LISInfoNode() {
            this.seqLen = 0;
            this.prev = -1;
        }

        public String toString() {
            return String.format("{L: %d,P: %d}", seqLen, prev);
        }

    }

    public LISSolverLegacy(Integer[] inputArr) {
        Objects.requireNonNull(inputArr);
        if (inputArr.length == 0) {
            throw new IllegalArgumentException("Input array shouldn't be empty!");
        }
        this.arr = inputArr;
    }

    public Integer[] findLIS() {
        LISInfoNode[] lisInfoArr = new LISInfoNode[arr.length];

        for (int i = 0; i < arr.length; i++) {
            LISInfoNode lisAtI = new LISInfoNode();
            for (int j = 0; j < i; j++) {
                LISInfoNode lisAtJ = lisInfoArr[j];
                if (arr[j] <= arr[i] && lisAtI.seqLen < lisAtJ.seqLen + 1) {
                    lisAtI.seqLen = lisAtJ.seqLen + 1;
                    lisAtI.prev = j;
                }
            }
            lisInfoArr[i] = lisAtI;
        }

        int maxLisIdx = IntStream.range(0, lisInfoArr.length).reduce(0,
                (i, j) -> lisInfoArr[i].seqLen > lisInfoArr[j].seqLen ? i : j);

        Integer[] lisArray = new Integer[lisInfoArr[maxLisIdx].seqLen + 1];
        for (int fillIdx = lisArray.length - 1; fillIdx >= 0; fillIdx--, maxLisIdx = lisInfoArr[maxLisIdx].prev) {
            lisArray[fillIdx] = arr[maxLisIdx];
        }
        return lisArray;
    }

    public static void main(String[] args) {
        List<Integer[]> allTestSequences = Arrays.asList(
                                               new Integer[] { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 },
                                               new Integer[] {12, 16, 18, 13, 14, 15, 11},
                                               new Integer[] { 69, 54, 19, 51, 16, 54, 64, 89, 72, 40, 31, 43, 1, 11, 82, 65, 75, 67, 25, 98, 31, 77, 55, 88, 85, 76, 35, 101, 44, 74, 29, 94, 72, 39, 20, 24, 23, 66, 16, 95, 5, 17, 54, 89, 93, 10, 7, 88, 68, 10, 11, 22, 25, 50, 18, 59, 79, 87, 7, 49, 26, 96, 27, 19, 67, 35, 50, 10, 6, 48, 38, 28, 66, 94, 60, 27, 76, 4, 43, 66, 14, 8, 78, 72, 21, 56, 34, 90, 89 });
        Integer[] testArr = new Integer[] { 3, 10, 2, 1, 20 };
        LISSolverLegacy solver = new LISSolverLegacy(testArr);
        System.out.format("LIS for %s is %s\n", Arrays.toString(testArr), Arrays.toString(solver.findLIS()));

        for (Integer[] arr: allTestSequences) {
        	solver = new LISSolverLegacy(arr);
            System.out.format("LIS for %s is %s\n", Arrays.toString(arr), Arrays.toString(solver.findLIS()));
        }
    }


}
