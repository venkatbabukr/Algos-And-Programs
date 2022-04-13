package com.venkat.algos.simple.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The solution logic is roughly similar to the double pointer/random pointer jumps solution.
 * 
 * This is a Greedy solution... Ref: https://www.interviewbit.com/blog/minimum-number-of-jumps/
 * 
 * Linearly iterate through the array using single step pointer. At each step/node update farthestJumpsPointer possible to 
 * Math.max(farthest found till now, stepsPtr + arr[stepsPtr]. Also keep a jumpsPointer - that can hold ref to farthest node that we can
 * reach by jumps. While iteration, whenever (stepsPtr == jumpsPtr) jump could have been taken, so, increment jumpsCnt (jumpsCnt++)
 * and update jumpsPtr to farthest found till now!
 *
 * @author vbkomarl
 */
public class ArrayMinJumpsSolver {

    private int[] arr;

    public ArrayMinJumpsSolver(int[] arrIp) {
        this.arr = arrIp;
    }

    public int findMinJumps() {
        int stepsPtr, jumpsPtr, farthestJumpsPtr, numJumps;
        for (numJumps = stepsPtr = jumpsPtr = farthestJumpsPtr = 0 ; stepsPtr < arr.length - 1 ; stepsPtr++) {
            farthestJumpsPtr = Math.max(farthestJumpsPtr, stepsPtr + arr[stepsPtr]);
            if (stepsPtr == jumpsPtr) {
                numJumps++;
                jumpsPtr = farthestJumpsPtr;
            }
        }
        return numJumps;
    }

    public List<Integer> findMinJumpsSeq() {
        int stepsPtr, jumpsPtr, farthestJumpsPtr, numJumps;
        List<Integer> minJumpsSeq = new ArrayList<>(arr.length);
        for (numJumps = stepsPtr = jumpsPtr = farthestJumpsPtr = 0 ; stepsPtr < arr.length - 1 ; stepsPtr++) {
            farthestJumpsPtr = Math.max(farthestJumpsPtr, stepsPtr + arr[stepsPtr]);
            if (stepsPtr == jumpsPtr) {
                numJumps++;
                minJumpsSeq.add(stepsPtr);
                jumpsPtr = farthestJumpsPtr;
            }
        }
        assert minJumpsSeq.size() == numJumps;
        return minJumpsSeq;
    }

    public static void main(String[] args) {
        List<int[]> testArrs = Arrays.asList(
                                   new int[] { 2, 3, 1, 1, 4 },
                                   new int[] { 1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9 },
                                   new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                                   new int[] { 0, 46, 46, 0, 2, 47, 1, 24, 45, 0, 0, 24, 18, 29, 27, 11, 0, 0, 40, 12, 4, 0, 0, 0, 49, 42, 13, 5, 12, 45, 10, 0, 29, 11, 22, 15, 17, 41, 34, 23, 11, 35, 0, 18, 47, 0, 38, 37, 3, 37, 0, 43, 50, 0, 25, 12, 0, 38, 28, 37, 5, 4, 12, 23, 31, 9, 26, 19, 11, 21, 0, 0, 40, 18, 44, 0, 0, 0, 0, 30, 26, 37, 0, 26, 39, 10, 1, 0, 0, 3, 50, 46, 1, 38, 38, 7, 6, 38, 27, 7, 25, 30, 0, 0, 36, 37, 6, 39, 40, 32, 41, 12, 3, 44, 44, 39, 2, 26, 40, 36, 35, 21, 14, 41, 48, 50, 21, 0, 0, 23, 49, 21, 11, 27, 40, 47, 49 });
        for (int[] arr : testArrs) {
            ArrayMinJumpsSolver solver = new ArrayMinJumpsSolver(arr);
            List<Integer> minJumpsSeq = solver.findMinJumpsSeq();
            System.out.format("Minimum jumps to reach end of %s = %s (count %d)%n", Arrays.toString(arr), minJumpsSeq, minJumpsSeq.size());
        }
    }

}
