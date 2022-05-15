package com.venkat.algos.simple.arrays;

import java.util.Arrays;
import java.util.List;

import com.venkat.utils.Pair;

public class MaxProductSubSequenceSolver {

    protected int[] arr;

    protected long maxProduct;
    
    protected Pair<Integer> maxPSIndices;

    public MaxProductSubSequenceSolver(int[] arrIp) {
        if (arrIp == null)
            throw new IllegalArgumentException("Array input can't be null!");
        this.arr = arrIp;
        computeMaxProdSubSeq();
    }

    public long computeMaxSubProduct() {
        long product = 1, maxProduct = 0;
        for (int i = 0 ; i < arr.length ; i++) {
            product *= arr[i];
            maxProduct = Math.max(maxProduct, product);
            // product ^= 0L;
            if (product == 0 && i + 1 < arr.length) product = 1;
        }
        return product;
    }

    protected void computeMaxProdSubSeq() {
    	long product, currMaxProd, maxProd;
        int currMaxBegin, currMaxEnd,
            maxBegin, maxEnd;

        maxBegin = maxEnd = currMaxBegin = currMaxEnd = 0;
        maxProd = currMaxProd = product = 1;

        for (int i = 0 ; i < arr.length ; i++) {
            product *= arr[i];
            if (product > currMaxProd) {
                currMaxEnd = i;
            } else if (product == 0) {
                if (currMaxProd > maxProd) {
                    maxProd = currMaxProd;
                    maxBegin = currMaxBegin;
                    maxEnd = currMaxEnd;
                }
                for (; i < arr.length && arr[i] == 0; i++);
                product = 1;
                currMaxBegin = i;
                i--;
            }
        }
        if (currMaxProd > maxProd) {
            maxProd = currMaxProd;
            maxBegin = currMaxBegin;
            maxEnd = currMaxEnd;
        }
        this.maxProduct = maxProd;
        this.maxPSIndices = new Pair<>(maxBegin, maxEnd);
    }

    public long getMaxProduct() {
    	return this.maxProduct;
    }

    public Pair<Integer> getMaxPSIndices() {
    	return this.maxPSIndices;
    }

    public static void main(String[] args) {
        List<int[]> testArrs = Arrays.asList(
                                   new int[] { -1, -2, 0 },
                                   new int[] { 0, -4, 0, -8 },
                                   new int[] { 6, -3, -10, 0, 2 },
                                   new int[] { -1, -3, -10, 0, 60 },
                                   new int[] { -2, -40, 0, -2, -3 },
                                   new int[] { 0, -3, 4, -10, -1, -6, 0, 8, -8, -6, -5, -5, 0, -3, -9, 1, 5, -8, 0, 6, 1, -6, -8, 3, 0, -8, -9, 6, 8, 5 },
                                   new int[] { -2, -3, 4, -1, -2, 1, 5, -3 });

       for (int[] arr : testArrs) {
           MaxProductSubSequenceSolver solver = new MaxProductSubSequenceSolver(arr);
           System.out.format("Max sum %l (computed sum %l) subseq for arr %s = %s%n", solver.getMaxProduct(), solver.computeMaxSubProduct(), Arrays.toString(arr), solver.getMaxPSIndices());
       }
    }

}
