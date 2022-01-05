package com.venkat.algos.dp.nqueens;

public class NQueensSolverApp {

    public static void main(String[] args) {
        for (int boardSize = 1 ; boardSize <= 20 ; boardSize++) {
            NQueensSolver solver = new NQueensSolverBB(boardSize);
            solver.solve(true);
        }
    }
}
