package com.venkat.algos.dp.nqueens;

import java.util.Arrays;

public interface NQueensSolver {

    int[] getQueenPlacements();

    void solve(boolean printSolution);

    default void printBoard() {
        int[] queenPlacements = getQueenPlacements();
        int bSize = queenPlacements.length;
        for (int row = 0 ; row < bSize ; row++) {
            int[] boardRow = new int[bSize];
            Arrays.fill(boardRow, 0);
            boardRow[queenPlacements[row]] = 1;
            System.out.println(Arrays.toString(boardRow));
        }
    }

}
