package com.venkat.algos.hacker;

import java.util.Arrays;

import com.venkat.utils.Pair;

/**
 * Solution for Hacker rank problem -
 * https://www.hackerrank.com/challenges/electronics-shop/problem
 * 
 * The crux of algorithm is to sort the two arrays - keyboard array and usb array.
 * To get the pair satisfying max allowed cost, perform forward lookup in keyboard array
 * and backward lookup in usb array, as long as the kb + usb cost is > max found till now
 * and < max allowed cost...
 * 
 * @author vbkomarl
 */
public class ElectronicsOptimalKeyBoardUSBSolver {

    public Pair<Integer> getMaxOptimalCombo(Integer[] kbCosts, Integer[] usbCosts, Integer maxAllowedCost) {
        Arrays.sort(kbCosts);
        Arrays.sort(usbCosts);

        int maxComboCost = 0;
        Pair<Integer> maxComboPair = null;
        for (int i = kbCosts.length - 1, j = 0; i > -1; i--) {
            for (; j < usbCosts.length; j++) {
                int currentComboCost = kbCosts[i] + usbCosts[j];
                if (currentComboCost > maxAllowedCost) break;
                else if (currentComboCost > maxComboCost) {
                    maxComboCost = currentComboCost;
                    maxComboPair = new Pair<>(kbCosts[i], usbCosts[j]);
                }
            }
        }
        return maxComboPair;
    }
    
    public static void main(String[] args) {
        ElectronicsOptimalKeyBoardUSBSolver solver = new ElectronicsOptimalKeyBoardUSBSolver();
        Integer[] kbCosts = new Integer[] { 3, 1 };
        Integer[] usbCosts = new Integer[] { 5, 2, 8 };
        int maxAllowedCost = 10;
        Pair<Integer> maxCombo = solver.getMaxOptimalCombo(kbCosts, usbCosts, maxAllowedCost);
        System.out.format("Max combo for %s keyboards & %s USBs with %d upper limit: %s%n",
                          Arrays.toString(kbCosts),
                          Arrays.toString(usbCosts), maxAllowedCost, maxCombo);

        kbCosts = new Integer[] { 4 };
        usbCosts = new Integer[] { 5 };
        maxAllowedCost = 5;
        maxCombo = solver.getMaxOptimalCombo(kbCosts, usbCosts, maxAllowedCost);
        System.out.format("Max combo for %s keyboards & %s USBs with %d upper limit: %s%n",
                          Arrays.toString(kbCosts),
                          Arrays.toString(usbCosts), maxAllowedCost, maxCombo);

    }

}
