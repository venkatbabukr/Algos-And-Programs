package com.venkat.algos.dp;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

/**
 * This is the fundamental backtracking algorithm ...
 * 
 * @author dell
 *
 */
public class NQueensSolver {

    private boolean diagonalIntersect(int r1, int c1, int r2, int c2) {
        return (r1 + c1 == r2 + c2 || r1 - c1 == r2 - c2);
    }

    private boolean isSafe(int queenCurrentRow, int queenCurrentCol, int[] queenPlacements) {
        if (queenCurrentRow >= queenPlacements.length || queenCurrentCol >= queenPlacements.length) {
            throw new IllegalArgumentException(String.format("Queen Row %d, Queen Col %d is out of bounds of: %d", queenCurrentRow, queenCurrentCol, queenPlacements.length));
        }
        for (int row = 0 ; row < queenCurrentRow ; row++) {
            int col = queenPlacements[row];
            if (col == queenCurrentCol || diagonalIntersect(row, col, queenCurrentRow, queenCurrentCol)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean placeQueenAtRow(int row, int[] queenPlacements) {
        boolean solved = row >= queenPlacements.length;
        if (!solved) {
            for (int col = 0 ; col < queenPlacements.length ; col++) {
                if (isSafe(row, col, queenPlacements)) {
                    queenPlacements[row] = col;
                    solved = placeQueenAtRow(row + 1, queenPlacements);
                    if (solved) {
                        break;
                    }
                }
            }
        }
        return solved;
    }
    
    private void printBoard(int[] queenPlacements) {
        int bSize = queenPlacements.length;
        for (int row = 0 ; row < bSize ; row++) {
            int[] boardRow = new int[bSize];
            Arrays.fill(boardRow, 0);
            boardRow[queenPlacements[row]] = 1;
            System.out.println(Arrays.toString(boardRow));
        }
    }

    public void solveNQueens(int boardSize, boolean printSolution) {
        int[] queenPlacements = new int[boardSize];
        Instant startTime = Instant.now();
        boolean boardSolutionPossible = placeQueenAtRow(0, queenPlacements);
        Instant endTime = Instant.now();
        String outputMessage = String.format("Queens placement solution for board size %d %s! Time taken: %.3f ms",
                                         boardSize, (boardSolutionPossible ? "exists" : "doesn't exist"),
                                         Duration.between(startTime, endTime).toMillis()/1000.0f);
        System.out.println(outputMessage);
        if (boardSolutionPossible && printSolution) {
            printBoard(queenPlacements);
        }
    }
    
    public static void main(String[] args) {
        NQueensSolver solver = new NQueensSolver();
        for (int i = 1 ; i <= 30 ; i++) {
            solver.solveNQueens(i, false);
        }
    }

}
