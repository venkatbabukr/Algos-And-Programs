package com.venkat.algos.dp;

import java.util.Arrays;

import com.venkat.algos.simple.MathExt;

public class MathDPExt {
    
    public static final int[] ugly(int n) {
        int[] uglyArr = new int[n];
        uglyArr[0] = 1;
        int ugly2Idx, ugly3Idx, ugly5Idx;
        ugly2Idx = ugly3Idx = ugly5Idx = 0;
        int next2Multiple = uglyArr[0] * 2,
            next3Multiple = uglyArr[0] * 3,
            next5Multiple = uglyArr[0] * 5;
        for (int i = 1 ; i < n ; i++) {
            int currUgly = MathExt.min(next2Multiple, next3Multiple, next5Multiple);
            uglyArr[i] = currUgly;
            if (currUgly == next2Multiple) {
                ugly2Idx++;
                next2Multiple = uglyArr[ugly2Idx] * 2;
            }
            if (currUgly == next3Multiple) {
                ugly3Idx++;
                next3Multiple = uglyArr[ugly3Idx] * 3;
            }
            if (currUgly == next5Multiple) {
                ugly5Idx++;
                next5Multiple = uglyArr[ugly5Idx] * 5;
            }
        }
        return uglyArr;
    }
    
    public static void main(String[] args) {
        int uglyArraySize = 15;
        System.out.format("Ugly array of %d numbers: %s\n",
            uglyArraySize,
            Arrays.toString(ugly(uglyArraySize)));
    }

}
