package com.venkat.algos.dp;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Stack;

import com.venkat.algos.simple.MathExt;
import com.venkat.utils.ArraysExt;
import com.venkat.utils.Pair;

public class MinCostPathSolver {

    private static final class LeastCostMatrix {
        
        /**
         * CCM stands for Cummulative cost matrix... Contains the cummulative cost of reaching
         * point (row, col) from (0, 0)
         */
        private int[][] CCM;

        public LeastCostMatrix(int[][] rcm) {
            this.CCM = new int[rcm.length][rcm[0].length];
            this.CCM[0][0] = rcm[0][0];
            // Initialize top row
            for (int col = 1; col < rcm[0].length; col++) {
                this.CCM[0][col] = this.CCM[0][col-1] + rcm[0][col];
            }
            // Initialize left most column
            for (int row = 1; row < rcm.length; row++) {
                this.CCM[row][0] = this.CCM[row - 1][0] + rcm[row][0];
            }
            // Iterate from Second row, second col till the end of matrix
            // to find all cummulative costs...
            for (int row = 1; row < rcm.length; row++) {
                for (int col = 1; col < rcm[row].length; col++) {
                    this.CCM[row][col] = MathExt.min(this.CCM[row - 1][col], this.CCM[row][col - 1],
                            this.CCM[row - 1][col - 1]) + rcm[row][col];
                }
            }
        }

        public int getCost(int r, int c) {
            return CCM[r][c];
        }
        
        public String toString() {
            return ArraysExt.to2DString(CCM);
        }

    }

    private int[][] rcm;
    private LeastCostMatrix lcm;

    public MinCostPathSolver(int[][] rawCM) {
        if (ArraysExt.isEmpty(rawCM)) {
            throw new IllegalArgumentException("Empty cost matrix given!");
        }
        this.rcm = rawCM;
        this.lcm = new LeastCostMatrix(rawCM);
    }
    
    public LeastCostMatrix getLCM() {
        return this.lcm;
    }

    public int getMinCostToReach(int m, int n) {
        return lcm.getCost(m, n);
    }
    
    public LinkedHashMap<Pair<Integer>, Integer> getMinCostPathToReach(int m, int n) {
        if (m < 0 || n < 0 || m >= this.rcm.length || n > this.rcm[0].length) {
            throw new IllegalArgumentException("Invalid coordinates given! Coordinates should be between the bounds of cost matrix!");
        }
        Stack<Pair<Integer>> revPathStack = new Stack<>();
        int cummulativeCost = lcm.getCost(m, n);
        while (cummulativeCost > 0) {
            revPathStack.push(new Pair<>(m, n));
            cummulativeCost -= rcm[m][n];
            if (m > 0 && n > 0 && lcm.getCost(m-1, n-1) == cummulativeCost) {
                m--;
                n--;
            } else if (m > 0 && lcm.getCost(m-1, n) == cummulativeCost) {
                m--;
            } else if (n > 0) {
                n--;
            }
        }
        LinkedHashMap<Pair<Integer>, Integer> pathMap = new LinkedHashMap<>();
        Collections.reverse(revPathStack);
        revPathStack.forEach(kp -> pathMap.put(kp, rcm[kp.getX()][kp.getY()]));
        return pathMap;
    }
    
    public static void main(String[] args) {
        int[][] costMatrix = new int[][] {{1, 2, 3}, {4, 8, 2}, {1, 5, 3}};
        MinCostPathSolver solver = new MinCostPathSolver(costMatrix);
        System.out.format("Cost matrix:\n%s\n\nLeast cost matrix:\n%s\n", ArraysExt.to2DString(costMatrix), solver.getLCM());
        System.out.format("Min cost path to reach point {2, 2}: %s", solver.getMinCostPathToReach(2, 2));
    }

}
