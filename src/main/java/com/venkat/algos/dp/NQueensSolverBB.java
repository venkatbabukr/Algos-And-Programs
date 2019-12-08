package com.venkat.algos.dp;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This is the backtracking algorithm with branch and bound that prunes the set of
 * possible lookups that can happen at a particular row...
 * 
 * @author dell
 *
 */
public class NQueensSolverBB {
    
    private final int[] queenPlacements;
    
    public NQueensSolverBB(int boardSize) {
    	if (boardSize < 1) {
    		throw new IllegalArgumentException("Board size can't be less than 1");
    	}
        queenPlacements = new int[boardSize];
    }

    /**
     * Prune the possible columsn for lookup at the particular row based on queen placements that have
     * happened upto the given <code>currentRow</code>
     * 
     * Logic:
     *     For every row in <code>queenPlacements</code> upto <code>currentRow</code>
     *         1. Remove column <code>queenPlacements[row]</code>
     *         2. Remove column <code>row + col - currentRow</code> because <code>currentRow + currentCol</code> will diagonally intersect with <code>row + col</code>
     *         3. Remove column <code>currentRow - row + col</code> because <code>currentRow - currentCol</code> will diagonally intersect with <code>row - col</code>
     * 
     * @param currentRow           The row where queen has to be placed...
     * @param queenPlacements      Array containing all queen placements in all rows...
     *                             Inside this method, we'll just be interested only to know queen
     *                             placements upto <code>currentRow</code>
     *                             
     * @return                     Pruned set of possible columns that can be looked up...
     */
    // TODO Need to see why this method is slower than pruneLookupColsForRow
    private Set<Integer> pruneLookupColsForRow2(int currentRow) {
        Set<Integer> pruneCols = new HashSet<>();
        for (int row = 0 ; row < currentRow ; row++) {
            int col = queenPlacements[row];
            pruneCols.add(col);
            pruneCols.add(row + col - currentRow);
            pruneCols.add(currentRow - row + col);
        }

        return IntStream.range(0, queenPlacements.length)
                   .filter(col -> !pruneCols.contains(col))
                   .boxed()
                   .collect(Collectors.toSet());
    }

    /**
     * Prune the possible columsn for lookup at the particular row based on queen placements that have
     * happened upto the given <code>currentRow</code>
     * 
     * Logic:
     *     For every row in <code>queenPlacements</code> upto <code>currentRow</code>
     *         1. Remove column <code>queenPlacements[row]</code>
     *         2. Remove column <code>row + col - currentRow</code> because <code>currentRow + currentCol</code> will diagonally intersect with <code>row + col</code>
     *         3. Remove column <code>currentRow - row + col</code> because <code>currentRow - currentCol</code> will diagonally intersect with <code>row - col</code>
     * 
     * @param currentRow           The row where queen has to be placed...
     * @param queenPlacements      Array containing all queen placements in all rows...
     *                             Inside this method, we'll just be interested only to know queen
     *                             placements upto <code>currentRow</code>
     *                             
     * @return                     Pruned set of possible columns that can be looked up...
     */
    private Set<Integer> pruneLookupColsForRow(int currentRow) {
        boolean[] possibleColsForRow = new boolean[queenPlacements.length];
        Arrays.fill(possibleColsForRow, true);
        for (int row = 0 ; row < currentRow ; row++) {
            int col = queenPlacements[row];
            possibleColsForRow[col] = false;
            int diagonalCol = row + col - currentRow;
            if (diagonalCol > -1 && diagonalCol < possibleColsForRow.length) {
                possibleColsForRow[diagonalCol] = false;
            }
            diagonalCol = currentRow - row + col;
            if (diagonalCol < possibleColsForRow.length) { // No need to add check for -1, coz (queenRow - row) is always +ve
                possibleColsForRow[diagonalCol] = false;
            }
        }

        return IntStream.range(0, possibleColsForRow.length)
                   .filter(col -> possibleColsForRow[col])
                   .boxed()
                   .collect(Collectors.toSet());
    }

    private boolean placeQueenAtRow(int row) {
        boolean solved = row >= queenPlacements.length;
        if (!solved) {
            Set<Integer> cols = pruneLookupColsForRow(row);
            for (int col : cols) {
                queenPlacements[row] = col;
                solved = placeQueenAtRow(row + 1);
                if (solved) {
                    break;
                }
            }
        }
        return solved;
    }
    
    private void printBoard() {
        int bSize = queenPlacements.length;
        for (int row = 0 ; row < bSize ; row++) {
            int[] boardRow = new int[bSize];
            Arrays.fill(boardRow, 0);
            boardRow[queenPlacements[row]] = 1;
            System.out.println(Arrays.toString(boardRow));
        }
    }

    public void solve(boolean printSolution) {
        Instant startTime = Instant.now();
        boolean boardSolutionPossible = placeQueenAtRow(0);
        Instant endTime = Instant.now();
        String outputMessage = String.format("Queens placement solution for board size %d %s! Time taken: %.3f ms",
                                         queenPlacements.length, (boardSolutionPossible ? "exists" : "doesn't exist"),
                                         Duration.between(startTime, endTime).toMillis()/1000.0f);
        System.out.println(outputMessage);

        if (boardSolutionPossible && printSolution) {
            printBoard();
        }
    }

    public static void main(String[] args) {
        for (int i = 1 ; i <= 20 ; i++) {
            NQueensSolverBB solver = new NQueensSolverBB(i);
            solver.solve(true);
        }
    }

}
