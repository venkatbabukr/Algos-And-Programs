package com.venkat.algos.simple.arrays;

import java.util.Arrays;

import com.venkat.utils.Pair;

/**
 * Logic is simple. Find the minimum element in array, and then find the maximum most element to the right of it.
 * The diff is the array max profit diff!
 *
 * @author vbkomarl
 */
public class SharesArrayMaxProfitFinder {

    public Pair<Integer> findMaxProfitDiff(int[] sharePrices) {
        int minPriceIdx = 0;
        for (int i = 1; i < sharePrices.length; i++) {
            if (sharePrices[i] < sharePrices[minPriceIdx]) {
                minPriceIdx = i;
            }
        }

        int maxPriceIdx = minPriceIdx;
        for (int i = maxPriceIdx ; i < sharePrices.length; i++) {
            if (sharePrices[i] > sharePrices[maxPriceIdx]) {
                maxPriceIdx = i;
            }
        }
        return new Pair<>(minPriceIdx, maxPriceIdx);
    }

    public Pair<Integer> findMaxProfitDiffEnhanced(int[] sharePrices) {
        int minPriceIdx = 0, maxPriceIdxRightOfMin = -1;
        for (int i = 1 ; i < sharePrices.length; i++) {
            if (sharePrices[i] < sharePrices[minPriceIdx]) {
                minPriceIdx = i;
                maxPriceIdxRightOfMin = -1;
            } else if (maxPriceIdxRightOfMin == -1 || sharePrices[i] > sharePrices[maxPriceIdxRightOfMin]) {
                maxPriceIdxRightOfMin = i;
            }
        }
        return new Pair<>(minPriceIdx,
                              Math.max(minPriceIdx, maxPriceIdxRightOfMin));
    }

    public static void main(String[] args) {
        SharesArrayMaxProfitFinder finder = new SharesArrayMaxProfitFinder();
        
        int[][] allTestCases = new int[][] {
            new int[] {10, 22, 5, 75, 65, 80},
            new int[] {12, 14, 17, 10, 14, 13, 12, 15},
            new int[] {100, 30, 15, 10, 8, 25, 80},
            new int[] {90, 80, 70, 60, 50}
        };

        for (int[] sharePrices : allTestCases) {
            Pair<Integer> maxProfitDiffIdces = finder.findMaxProfitDiff(sharePrices);
            int maxProfit = sharePrices[maxProfitDiffIdces.getY()] - sharePrices[maxProfitDiffIdces.getX()];
            System.out.format("Shares=%s, Maxprofit=%d between {%d[%d] - %d[%d]}%n",
                                  Arrays.toString(sharePrices), maxProfit,
                                  sharePrices[maxProfitDiffIdces.getX()], maxProfitDiffIdces.getX(),
                                  sharePrices[maxProfitDiffIdces.getY()], maxProfitDiffIdces.getY());
        }

        // Enhanced version...
        System.out.println("-------------------------");
        System.out.println("Moderate Enhanced version");
        System.out.println("-------------------------");
        for (int[] sharePrices : allTestCases) {
            Pair<Integer> maxProfitDiffIdces = finder.findMaxProfitDiffEnhanced(sharePrices);
            int maxProfit = sharePrices[maxProfitDiffIdces.getY()] - sharePrices[maxProfitDiffIdces.getX()];
            System.out.format("Shares=%s, Maxprofit=%d between {%d[%d] - %d[%d]}%n",
                                  Arrays.toString(sharePrices), maxProfit,
                                  sharePrices[maxProfitDiffIdces.getX()], maxProfitDiffIdces.getX(),
                                  sharePrices[maxProfitDiffIdces.getY()], maxProfitDiffIdces.getY());
        }

    }

}
