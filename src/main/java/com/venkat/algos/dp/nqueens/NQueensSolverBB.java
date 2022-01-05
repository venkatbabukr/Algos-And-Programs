package com.venkat.algos.dp.nqueens;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * This is the backtracking algorithm with branch and bound that prunes the set of
 * possible lookups that can happen at a particular row...
 * 
 * @author venkat
 */
public class NQueensSolverBB extends NQueensSolverBacktracking {

    public NQueensSolverBB(int boardSize) {
        super(boardSize);
    }

    /**
     * Prune the possible columsn for lookup at the particular row based on queen placements that have
     * happened upto the given <code>currentRow</code>
     * <br><br>
     * Logic: For every row in <code>queenPlacements</code> upto <code>currentRow</code>
     *         <ol>
     *           <li>Remove column <code>queenPlacements[row]</code></li>
     *           <li>Remove column <code>row + col - currentRow</code> because <code>currentRow + currentCol</code> will diagonally intersect with <code>row + col</code></li>
     *           <li>Remove column <code>currentRow - row + col</code> because <code>currentRow - currentCol</code> will diagonally intersect with <code>row - col</code></li>
     *         </ol>
     * 
     * @param currentRow           The row where queen has to be placed...
     * @return                     Pruned set of possible columns that can be looked up...
     */
    protected int[] pruneLookupColsForRow(int currentRow) {
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
                   .toArray();
    }

    @Override
    protected boolean placeQueenAtRow(int row) {
        boolean solved = row >= queenPlacements.length;
        if (!solved) {
            int[] prunedCols = pruneLookupColsForRow(row);
            for (int col : prunedCols) {
                queenPlacements[row] = col;
                solved = placeQueenAtRow(row + 1);
                if (solved) {
                    break;
                }
            }
        }
        return solved;
    }

}
