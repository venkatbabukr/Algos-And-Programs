package com.venkat.algos.dp.nqueens.more;

import com.venkat.algos.dp.nqueens.NQueensSolver;
import com.venkat.algos.dp.nqueens.NQueensSolverBB;

public class NQueensSolverApp {

    public static void main(String[] args) {
        for (int boardSize = 1 ; boardSize <= 20 ; boardSize++) {
            NQueensSolver solver = new NQueensSolverBB(boardSize);
            solver.solve(true);
        }
    }
}
