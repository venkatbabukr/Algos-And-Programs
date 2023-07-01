package com.venkat.algos.dp.matrix;

/**
 * Find minimum cost from r1, c1 to r2, c2 where we can only go forward and down. IF we hit a Zero, that is a wall and we can't go in that direction.
 * 
 * @author venkateshbabukr
 */
public class MinCostPathSolverWithZeroBound extends MinCostPathSolver {

	public MinCostPathSolverWithZeroBound(int[][] cm) {
		super(cm);
	}
	
	private int getCost(int mcmVal, int cmVal) {
		int cost = mcmVal >= Integer.MAX_VALUE || cmVal == 0 ? Integer.MAX_VALUE : mcmVal + cmVal;
		return cost < 0 ? Integer.MAX_VALUE : cost;
	}
	
	protected int[][] buildMCM() {
    	int[][] mcm = new int[cm.length][cm[0].length];
    	mcm[0][0] = cm[0][0];
    	// First row fill.
    	for (int row = 1 ; row < cm.length; row++) {
    		mcm[row][0] = getCost(mcm[row - 1][0], cm[row][0]);
    	}
    	// First col fill.
    	for (int col = 1 ; col < cm[0].length; col++) {
    		mcm[0][col] = getCost(mcm[0][col - 1], cm[0][col]);
    	}
    	// Remaining row fills...
    	for (int row = 1 ; row < cm.length ; row ++) {
    		for (int col = 1 ; col < cm[row].length ; col++) {
    			mcm[row][col] = Math.min(getCost(mcm[row - 1][col], cm[row][col]), getCost(mcm[row][col - 1], cm[row][col]));
    		}
    	}
    	return mcm;
	}

}
