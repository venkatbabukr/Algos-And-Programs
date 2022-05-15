package com.venkat.algos.dp.matrix;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Stack;

import com.venkat.algos.simple.MathExt;
import com.venkat.utils.Pair;
import com.venkat.utils.ext.ArraysExt;

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

        // Iterate and fill cummulative costs...
        for (int row = 1; row < mcm.length; row++) {
            for (int col = 1; col < mcm[row].length; col++) {
                mcm[row][col] = MathExt.min(mcm[row - 1][col],
                                            mcm[row][col - 1],
                                            mcm[row - 1][col - 1])
                                    + cm[row - 1][col - 1];
            }
        }
    	return mcm;
    }

    public int[][] getMinCostMatrix() {
        return this.mcm;
    }

    public int getMinCostToNode(int m, int n) {
        if (m < 0 || n < 0 || m > this.cm.length || n > this.cm[0].length) {
            throw new IllegalArgumentException(
                    "Invalid coordinates given! Coordinates should be between the bounds of cost matrix!");
        }
        return this.mcm[m + 1][n + 1];
    }

    public LinkedHashMap<Pair<Integer>, Integer> getMinCostPathToNode(int m, int n) {
        if (m < 0 || n < 0 || m >= this.cm.length || n > this.cm[0].length) {
            throw new IllegalArgumentException(
                    "Invalid coordinates given! Coordinates should be between the bounds of cost matrix!");
        }
        Stack<Pair<Integer>> revPathStack = new Stack<>();
        int cummulativeCost = this.mcm[m + 1][n + 1];
        while (cummulativeCost > 0) {
            revPathStack.push(new Pair<>(m, n));
            cummulativeCost -= this.cm[m][n];
            if (this.mcm[m][n] == cummulativeCost) {
                m--;
                n--;
            } else if (this.mcm[m][n + 1] == cummulativeCost) {
                m--;
            } else {
                n--;
            }
        }
        LinkedHashMap<Pair<Integer>, Integer> pathMap = new LinkedHashMap<>();
        while (!revPathStack.isEmpty()) {
            Pair<Integer> nodePair = revPathStack.pop();
        	pathMap.put(nodePair, this.cm[nodePair.getX()][nodePair.getY()]);
        }
        /*
        Collections.reverse(revPathStack);
        revPathStack.forEach(nodepair -> pathMap.put(nodepair, this.cm[nodepair.getX()][nodepair.getY()]));
        */
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
