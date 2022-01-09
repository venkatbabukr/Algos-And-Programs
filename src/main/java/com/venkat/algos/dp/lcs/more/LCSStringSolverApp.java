package com.venkat.algos.dp.lcs.more;

import java.util.Arrays;
import java.util.List;

import com.venkat.algos.dp.lcs.LCSStringSolver;
import com.venkat.java.exercises.common.ExercisesUtil;
import com.venkat.utils.Pair;

public class LCSStringSolverApp {

    public static void main(String[] args) {
        
        final List<Pair<String>> allTestStrings = Arrays.asList(
                                                      new Pair<>("AGGTAB", "GXTXAYB"),
                                                      new Pair<>("ABCD", "ACBAD"),
                                                      new Pair<>("", "ACBAD"));

        List<Runnable> allSolvers = Arrays.asList(
            () -> {
                System.out.println(ExercisesUtil.getFormattedExerciseTitle("LCSStringSolver"));
                for (Pair<String> strs : allTestStrings) {
                    LCSStringSolver solver = new LCSStringSolver(strs.getX(), strs.getY());
                    System.out.format("LCS of %s, %s = {Single: %s, All: %s}%n", strs.getX(), strs.getY(), solver.findSingleLCS(), solver.findAllLCS());
                }
                System.out.println("");
            },
            () -> {
                System.out.println(ExercisesUtil.getFormattedExerciseTitle("LCSStringSolverSpaceOptimized"));
                for (Pair<String> strs : allTestStrings) {
                    LCSStringSolver solver = new LCSStringSolverSpaceOptimized(strs.getX(), strs.getY());
                    System.out.format("LCS of %s, %s = {Single: %s, All: %s}%n", strs.getX(), strs.getY(), solver.findSingleLCS(), solver.findAllLCS());
                }
                System.out.println("");
            });

        allSolvers.stream().forEach(r -> r.run());
    }

}
