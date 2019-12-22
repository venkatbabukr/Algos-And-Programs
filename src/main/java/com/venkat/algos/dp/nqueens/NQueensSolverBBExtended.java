package com.venkat.algos.dp.nqueens;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class NQueensSolverBBExtended extends NQueensSolverBB {

    public NQueensSolverBBExtended(int boardSize) {
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
     * @return                     Pruned array of possible columns that can be looked up...
     */
    // TODO Need to see why this method is slower than pruneLookupColsForRow
    private int[] pruneLookupColsForRowUsingSets(int currentRow) {
        Set<Integer> pruneCols = new HashSet<>();
        for (int row = 0 ; row < currentRow ; row++) {
            int col = queenPlacements[row];
            pruneCols.add(col);
            pruneCols.add(row + col - currentRow);
            pruneCols.add(currentRow - row + col);
        }

        return IntStream.range(0, queenPlacements.length)
                   .filter(col -> !pruneCols.contains(col))
                   .toArray();
    }

    /**
     * The logic here is same as {@link NQueensSolverBB#pruneLookupColsForRow(int)}, just getting rid of if conditions that
     * existed inside for loop by using fairly large possibleColsForRow array - possibleColsForRow is array of size
     * boardSize * 3 ... and the middle range of this array will finally contain list of possible columns...
     * 
     * @param currentRow           The row where queen has to be placed...
     * @return                     Pruned array of possible columns that can be looked up...
     */
    private int[] pruneLookupColsForRowUsingExtendedArray(int currentRow) {
        // This is a function same as pruneLookupColsForRow, but uses extra space to get rid of un-necessary if-else conditions
        // Use an array of size - boardSize (queenPlacements.length) * 3 ... The middle set of (0, boardSize (queenPlacements.length) - 1)
        // will give us the exact list of columns that can be searched for ...
        boolean[] possibleColsForRow = new boolean[queenPlacements.length * 3];
        Arrays.fill(possibleColsForRow, true);
        for (int row = 0 ; row < currentRow ; row++) {
            int col = queenPlacements[row];
            possibleColsForRow[queenPlacements.length + col] = false;

            int diagonalCol = row + col - currentRow;
            possibleColsForRow[queenPlacements.length + diagonalCol] = false;
            diagonalCol = currentRow - row + col;
            possibleColsForRow[queenPlacements.length + diagonalCol] = false;
        }

        return IntStream.range(0, queenPlacements.length)
                   .filter(col -> possibleColsForRow[queenPlacements.length + col])
                   .toArray();
    }

    @Override
    protected int[] pruneLookupColsForRow(int currentRow) {
        return pruneLookupColsForRowUsingExtendedArray(currentRow);
    }

}
