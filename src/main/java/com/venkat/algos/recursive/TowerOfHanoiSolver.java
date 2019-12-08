package com.venkat.algos.recursive;

public class TowerOfHanoiSolver {
    
    private final int totalDisks;

    public TowerOfHanoiSolver(int total) {
        this.totalDisks = total;
    }

    private void solve(int diskNum, char fromPole, char toPole, char intermediatePole) {
        if (diskNum == 1) {
            System.out.format("Move disk %d from %c to %c\n", diskNum, fromPole, toPole);
            return;
        }
        solve(diskNum-1, fromPole, intermediatePole, toPole);
        System.out.format("Move disk %d from %c to %c\n", diskNum, fromPole, toPole);
        solve(diskNum-1, intermediatePole, toPole, fromPole);
    }

    public void solve() {
        solve(totalDisks, 'A', 'C', 'B');
    }

    public static void main(String[] args) {
        TowerOfHanoiSolver solver = new TowerOfHanoiSolver(3);
        solver.solve();
    }

}
