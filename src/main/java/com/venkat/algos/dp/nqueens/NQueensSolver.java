package com.venkat.algos.dp.nqueens;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public abstract class NQueensSolver {

    protected abstract boolean placeQueenAtRow(int row);

    public abstract int[] getQueenPlacements();

    public final void solve(boolean printSolution) {
        Instant startTime = Instant.now();
        boolean boardSolutionPossible = placeQueenAtRow(0);
        Instant endTime = Instant.now();
        String outputMessage = String.format("Queens placement solution for board size %d %s! Time taken: %.3f ms",
                                         getQueenPlacements().length, (boardSolutionPossible ? "exists" : "doesn't exist"),
                                         Duration.between(startTime, endTime).toMillis()/1000.0f);
        System.out.println(outputMessage);
        if (boardSolutionPossible && printSolution) {
            printBoard();
        }
    }

    public final void printBoard() {
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
