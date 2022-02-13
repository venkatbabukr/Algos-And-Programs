package com.venkat.algos.dp.matrix;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Stack;

import com.venkat.algos.simple.MathExt;
import com.venkat.utils.ArraysExt;
import com.venkat.utils.Pair;

public class MinCostPathSolver {

    protected int[][] cm;
    protected int[][] mcm;

    public MinCostPathSolver(int[][] cm) {
        if (ArraysExt.isEmpty(cm)) {
            throw new IllegalArgumentException("Empty cost matrix given!");
        }
        this.cm = cm;
        this.mcm = buildMCM();
    }

    protected int[][] buildMCM() {
    	int[][] mcm = new int[cm.length + 1][cm[0].length + 1];

        // First fills...
    	Arrays.fill(mcm[0], 0);
        for (int i = 1; i < mcm.length; i++) mcm[i][0] = 0;

        this.mcm[0][0] = cm[0][0];
        // Initialize top row
        for (int col = 1; col < cm[0].length; col++) {
            this.mcm[0][col] = this.mcm[0][col - 1] + cm[0][col];
        }
        // Initialize left most column
        for (int row = 1; row < cm.length; row++) {
            this.mcm[row][0] = this.mcm[row - 1][0] + cm[row][0];
        }
        // Iterate from Second row, second col till the end of matrix
        // to find all cummulative costs...
        for (int row = 1; row < cm.length; row++) {
            for (int col = 1; col < cm[row].length; col++) {
                this.mcm[row][col] = MathExt.min(this.mcm[row - 1][col], this.mcm[row][col - 1],
                        this.mcm[row - 1][col - 1]) + cm[row][col];
            }
        }
    	return null;
    }

    public int[][] getMinCostMatrix() {
        return this.mcm;
    }

    public int getMinCostToNode(int m, int n) {
        if (m < 0 || n < 0 || m > this.mcm.length || n > this.mcm[0].length) {
            throw new IllegalArgumentException(
                    "Invalid coordinates given! Coordinates should be between the bounds of cost matrix!");
        }
        return this.mcm[m][n];
    }

    public LinkedHashMap<Pair<Integer>, Integer> getMinCostPathToNode(int m, int n) {
        if (m < 0 || n < 0 || m >= this.cm.length || n > this.cm[0].length) {
            throw new IllegalArgumentException(
                    "Invalid coordinates given! Coordinates should be between the bounds of cost matrix!");
        }
        Stack<Pair<Integer>> revPathStack = new Stack<>();
        int cummulativeCost = this.mcm[m][n];
        while (cummulativeCost > 0) {
            revPathStack.push(new Pair<>(m, n));
            cummulativeCost -= this.cm[m][n];
            if (m > 0 && n > 0 && this.mcm[m - 1][n - 1] == cummulativeCost) {
                m--;
                n--;
            } else if (m > 0 && this.mcm[m - 1][n] == cummulativeCost) {
                m--;
            } else if (n > 0) {
                n--;
            }
        }
        LinkedHashMap<Pair<Integer>, Integer> pathMap = new LinkedHashMap<>();
        Collections.reverse(revPathStack);
        revPathStack.forEach(nodepair -> pathMap.put(nodepair, this.cm[nodepair.getX()][nodepair.getY()]));
        return pathMap;
    }

    public static void main(String[] args) {
        int[][] costMatrix = new int[][] { { 1, 2, 3 }, { 4, 8, 2 }, { 1, 5, 3 } };
        MinCostPathSolver solver = new MinCostPathSolver(costMatrix);
        System.out.format("Cost matrix:%n%s%n%nMinimum cost matrix:%n%s%n%n", ArraysExt.to2DString(costMatrix),
                ArraysExt.to2DString(solver.getMinCostMatrix()));
        for (int row = 1; row < costMatrix.length; row++) {
            for (int col = 1; col < costMatrix[row].length; col++) {
                System.out.format("Min path of cost %d to node {%d, %d}: %s%n", solver.getMinCostToNode(row, col), row,
                        col, solver.getMinCostPathToNode(row, col));
            }
        }
    }

}
