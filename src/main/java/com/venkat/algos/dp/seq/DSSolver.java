package com.venkat.algos.dp.seq;

import java.util.Arrays;
import java.util.List;

import com.venkat.utils.Pair;

public class DSSolver {
    
    private char[] s1Chars;
    
    private char[] s2Chars;
    
    private int[][] dsm;
    
    public DSSolver(String s1, String s2) {
        if (s1 == null || s2 == null)
            throw new IllegalArgumentException("Both strings should be provided!");
        s1Chars = s1.toCharArray();
        s2Chars = s2.toCharArray();
        dsm = buildDSM();
    }

    private int[][] buildDSM() {
        int[][] dsm = new int[s2Chars.length + 1][s1Chars.length + 1];
        
        // Initialize...
        for (int j = 1 ; j < dsm.length ; j++) dsm[j][0] = 0;
        for (int i = 0 ; i < dsm[0].length ; i++) dsm[0][i] = 1;
        
        // Build the remaining matrix
        for (int j = 1; j < s2Chars.length + 1 ; j++) {
            for (int i = 1 ; i < s1Chars.length + 1 ; i++) {
                if (s2Chars[j - 1] != s1Chars[i - 1])
                    dsm[j][i] = dsm[j][i-1];
                else
                    dsm[j][i] = dsm[j][i-1] + dsm[j-1][i-1];
            }
        }
        return dsm;
    }

    public int getNumDistinctCombinations() {
        return dsm[s2Chars.length][s1Chars.length];
    }

    public static void main(String[] args) {
        List<Pair<String>> testStrs = Arrays.asList(
                new Pair<>("rabbbit", "rabbit"),
                new Pair<>("banana", "ban"));
        for (Pair<String> testPairs : testStrs) {
            String s1 = testPairs.getX();
            String s2 = testPairs.getY();
            DSSolver solver = new DSSolver(s1, s2);
            System.out.format("Number of distinct combinations of %s in %s is %d%n", s2, s1, solver.getNumDistinctCombinations());
        }
    }

}
