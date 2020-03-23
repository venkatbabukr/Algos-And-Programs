package com.venkat.algos.dp.lcs;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LCSStringSolver {


    private static final Logger LOGGER = LoggerFactory.getLogger(LCSSolver.class);

    private final char[] seq1;
    private final char[] seq2;

    private static class LCSCountMatrix {
        private final int[][] L;

        public LCSCountMatrix(int str1Len, int str2Len) {
            this.L = new int[str1Len][str2Len];
        }

        public int get(int i, int j) {
            return i > -1 && j > -1 ? L[i][j] : 0;
        }

        public void set(int i, int j, int val) {
            L[i][j] = val;
        }

        public String toString() {
            return Arrays.stream(L)
                       .sequential()
                       .map(row -> Arrays.toString(row))
                       .collect(Collectors.joining(System.lineSeparator()));
        }

    }

    public LCSStringSolver(String s1, String s2) {
        this.seq1 = s1.toCharArray();
        this.seq2 = s2.toCharArray();
    }

    public String findLCS() {
        char[] lcsChars = null;
        if (seq1 != null && seq2 != null) {
            LCSCountMatrix countMatrix = new LCSCountMatrix(seq1.length, seq2.length);
            for (int i = 0; i < seq1.length; i++) {
                for (int j = 0; j < seq2.length; j++) {
                    int lcs_ij = seq1[i] == seq2[j] ?
                                     1 + countMatrix.get(i-1, j-1) :
                                     Math.max(countMatrix.get(i-1, j), countMatrix.get(i, j-1));
                    countMatrix.set(i, j, lcs_ij);
                }
            }
            LOGGER.debug("LCSCountMatrix:\n{}\n", countMatrix);

            int i = seq1.length - 1;
            int j = seq2.length - 1;
            int lcsLength = countMatrix.get(i, j);
            lcsChars = new char[lcsLength];

            for (int lcsIdx = lcsLength - 1; lcsIdx >= 0; ) {
                if (seq1[i] == seq2[j]) {
                    lcsChars[lcsIdx] = seq1[i];
                    LOGGER.debug("Added character {} from s1[{}], s2[{}] to lcsChars[{}]={}", seq1[i], i, j, lcsIdx, lcsChars);
                    i--;
                    j--;
                    lcsIdx--;
                } else if (countMatrix.get(i-1, j) > countMatrix.get(i, j-1))
                    i--;
                else
                    j--;
                LOGGER.debug("At the end of iteration, i={}, j={}, lcsIdx={}", i, j, lcsIdx);
            }
        }
        return Optional
                   .ofNullable(lcsChars)
                   .map(chars -> new String(chars))
                   .orElse(null);
    }

    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
        LCSStringSolver solver = new LCSStringSolver(s1, s2);
        System.out.format("LCS of %s, %s = %s", s1, s2, solver.findLCS());
    }

}
