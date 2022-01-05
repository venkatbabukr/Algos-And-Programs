package com.venkat.algos.dp.lcs;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.venkat.utils.ArraysExt;

public class LCSStringSolver {


    private static final Logger LOGGER = LoggerFactory.getLogger(LCSSolver.class);

    private final char[] seq1;
    private final char[] seq2;

    public LCSStringSolver(String s1, String s2) {
        this.seq1 = s1.toCharArray();
        this.seq2 = s2.toCharArray();
    }

    public String findLCS() {
        char[] lcsChars = null;
        if (seq1 != null && seq1.length > 0 && seq2 != null && seq2.length > 0) {
            int[][] lcm = new int[seq1.length][seq2.length];

            // First fills...
            lcm[0][0] = seq1[0] == seq2[0] ? 1 : 0;
            // First row fill...
            for (int j = 1 ; j < seq2.length ; j++) {
                lcm[0][j] = seq1[0] == seq2[j] ? 1 : lcm[0][j-1];
            }
            // First col fill...
            for (int i = 1 ; i < seq1.length ; i++) {
                lcm[i][0] = seq1[i] == seq2[0] ? 1 : lcm[i-1][0];
            }

            // Remaining matrix fill...
            for (int i = 1; i < seq1.length; i++) {
                for (int j = 1; j < seq2.length; j++) {
                    lcm[i][j] = seq1[i] == seq2[j] ?
                                     1 + lcm[i-1][j-1] :
                                     Math.max(lcm[i-1][j], lcm[i][j-1]);
                }
            }
            LOGGER.debug("LCS CountMatrix:\n{}\n", ArraysExt.to2DString(lcm));

            int i = seq1.length - 1;
            int j = seq2.length - 1;
            int lcsLength = lcm[i][j];
            lcsChars = new char[lcsLength];

            for (int lcsIdx = lcsLength - 1; lcsIdx >= 0; ) {
                if (seq1[i] == seq2[j]) {
                    lcsChars[lcsIdx] = seq1[i];
                    LOGGER.debug("Added character {} from s1[{}], s2[{}] to lcsChars[{}]={}", seq1[i], i, j, lcsIdx, lcsChars);
                    i--;
                    j--;
                    lcsIdx--;
                } else if ((i > 0 ? lcm[i-1][j] : 0) > (j > 0 ? lcm[i][j-1] : 0))
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
        System.out.format("LCS of %s, %s = %s%n", s1, s2, solver.findLCS());

        s1 = "ABCD";
        s2 = "ACBAD";
        solver = new LCSStringSolver(s1, s2);
        System.out.format("LCS of %s, %s = %s", s1, s2, solver.findLCS());
    }

}
