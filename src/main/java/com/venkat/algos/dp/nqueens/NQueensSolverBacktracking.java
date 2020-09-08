package com.venkat.algos.dp.nqueens;

import java.time.Duration;
import java.time.Instant;

/**
 * This is the fundamental backtracking algorithm ...
 * 
 * @author venkat
 */
public class NQueensSolverBacktracking implements NQueensSolver {
    
    protected final int[] queenPlacements;

    public NQueensSolverBacktracking(int boardSize) {
        if (boardSize < 1) {
            throw new IllegalArgumentException("Board size can't be less than 1");
        }
        queenPlacements = new int[boardSize];
    }

    public int[] getQueenPlacements() {
        return queenPlacements;
    }

    /**
     * (r1, c1) diagonally intersect with (r2, c2) if any one of the following two conditions satisfy:
     * <ul>
     *   <li>Condition 1: (r1-c1) == (r2-c2)</li>
     *   <li>Condition 2: (r1+c1) == (r2+c2)</li>
     * </ul>
     * <br>
     * @param r1    r1 of (r1, c1)
     * @param c1    c1 of (r1, c1)
     * @param r2    r2 of (r2, c2)
     * @param c2    c2 of (r2, c2)
     * 
     * @return      true if (r1, c1) diagonally intersect (r2, c2), false otherwise
     */
    private boolean diagonalIntersect(int r1, int c1, int r2, int c2) {
        return (r1 + c1 == r2 + c2 || r1 - c1 == r2 - c2);
    }

    /**
     * Queen placement at <code>(queenPlacementRow, queenPlacementCol)</code> is safe, only if queen placements in all rows
     * from <code>0 - queenPlacementRow</code> don't hit each other...
     * <br><br>
     * These are the only conditions that have to be satisfied:
     * <ul>
     *   <li> (col = queenPlacements[row]) != queenPlacementCol</li>
     *   <li> (row, col = queenPlacements[row]) doesn't diagonally intersect with (queenPlacementRow, queenPlacementCol)</li>
     * </ul>
     * <br>
     * @param queenPlacementRow     The row where queen has to be placed
     * @param queenPlacementCol     The column where queen has to be placed
     * 
     * @return                      true if the queen placement at (queenPlacementRow, queenPlacementCol) is safe, false otherwise.
     */
    private boolean isSafe(int queenPlacementRow, int queenPlacementCol) {
        if (queenPlacementRow >= queenPlacements.length || queenPlacementCol >= queenPlacements.length) {
            throw new IllegalArgumentException(String.format("Queen Row %d, Queen Col %d is out of bounds of: %d", queenPlacementRow, queenPlacementCol, queenPlacements.length));
        }
        for (int row = 0 ; row < queenPlacementRow ; row++) {
            int col = queenPlacements[row];
            if (col == queenPlacementCol || diagonalIntersect(row, col, queenPlacementRow, queenPlacementCol)) {
                return false;
            }
        }
        return true;
    }

    /**
     * DP Backtracking solution with recursion & termination conditions as follows:
     * <br><br>
     * <b>Termination condition:</b> If we have reached row > board length (i.e. >= queenPlacements.length), then all queen placements are solved
     * <br><br>
     * <b>Otherwise Recursion</b>:
     * <ul>
     *   <li>for each col in given row, if (isSafe(row, col)), then Place queen at col</li>
     *   <li>Solve for (row + 1)
     *     <ul>
     *       <li> If can't solve, remove the queen placement at (row, col) and continue until we get solution</li>
     *     </ul>
     *   </li>
     * </ul>
     * <br>
     * @param row         The row at which queen has to be placed
     * @return            true if we can find a solution for placement at row, false otherwise...
     */
    protected boolean placeQueenAtRow(int row) {
        boolean solved = row >= queenPlacements.length;
        if (!solved) {
            for (int col = 0 ; col < queenPlacements.length && !solved ; col++) {
                if (isSafe(row, col)) {
                    queenPlacements[row] = col;
                    solved = placeQueenAtRow(row + 1);
                }
            }
        }
        return solved;
    }

    @Override
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

}
