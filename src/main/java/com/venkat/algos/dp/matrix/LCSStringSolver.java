package com.venkat.algos.dp.matrix;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.venkat.utils.ext.ArraysExt;

public class LCSStringSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(LCSStringSolver.class);

    protected final char[] s1Chars;
    protected final char[] s2Chars;
    protected final int[][] lcm;

    public LCSStringSolver(String s1, String s2) {
        if (s1 == null || s2 == null)
            throw new IllegalArgumentException("Both strings should be provided!");
        this.s1Chars = s1.toCharArray();
        this.s2Chars = s2.toCharArray();
        this.lcm = buildLCM(this.s1Chars, this.s2Chars);
    }

    protected int[][] buildLCM(char[] s1Chars, char[] s2Chars) {
        int [][] lcm = new int[s1Chars.length + 1][s2Chars.length + 1];

        // Initialize lcm array...
        for (int j = 0 ; j < lcm[0].length ; j++) lcm[0][j] = 0;
        for (int i = 1 ; i < lcm.length ; i++) lcm[i][0] = 0;

        // Remaining matrix fill...
        for (int i = 0; i < s1Chars.length; i++) {
            for (int j = 0; j < s2Chars.length; j++) {
                lcm[i + 1][j + 1] = s1Chars[i] == s2Chars[j] ?
                                        1 + lcm[i][j] :
                                        Math.max(lcm[i][j + 1], lcm[i + 1][j]);
            }
        }
        LOGGER.debug("LCS CountMatrix:\n{}\n", ArraysExt.to2DString(lcm));
        return lcm;
    }

    /**
     * Finds all LCS. Algo logic courtesy - https://www.techiedelight.com/longest-common-subsequence-finding-lcs/
     * 
     * @param i
     * @param j
     * @param csLenAtIJ
     * @param lcsLen
     * @return
     */
    private List<char[]> findAllLCSTill(int i, int j, int csLenAtIJ, int lcsLen) {
        if (csLenAtIJ == 0)
            return Arrays.asList(new char[lcsLen]);
        else {
            if (s1Chars[i - 1] == s2Chars[j - 1]) {
                List<char[]> allLCS = findAllLCSTill(i - 1, j - 1, csLenAtIJ - 1, lcsLen);
                for (char[] seqs : allLCS) {
                    seqs[csLenAtIJ - 1] = s1Chars[i - 1];
                }
                return allLCS;
            } else if (lcm[i - 1][j] > lcm[i][j - 1]) {
                return findAllLCSTill(i - 1, j, csLenAtIJ, lcsLen);
            } else if (lcm[i][j - 1] > lcm[i - 1][j]) {
                return findAllLCSTill(i, j - 1, csLenAtIJ, lcsLen);
            } else {
                // This is when lcm[i - 1][j] == lcm[i][j - 1]
                List<char[]> allLcsFromS1Sub = findAllLCSTill(i - 1, j, csLenAtIJ, lcsLen);
                List<char[]> allLcsFromS2Sub = findAllLCSTill(i, j - 1, csLenAtIJ, lcsLen);
                return Stream.concat(allLcsFromS1Sub.stream(),
                                     allLcsFromS2Sub.stream())
                             .collect(Collectors.toList());
            }
        }
    }

    public List<String> findAllLCS() {
        int lcsLen = lcm[s1Chars.length][s2Chars.length];
        return findAllLCSTill(s1Chars.length, s2Chars.length, lcsLen, lcsLen)
                   .stream()
                   .map(chars -> new String(chars))
                   .distinct()
                   .collect(Collectors.toList());
    }

    public String findSingleLCS() {
        int lcsLen = lcm[s1Chars.length][s2Chars.length];
        char[] lcsChars = new char[lcsLen];
        for (int i = s1Chars.length, j = s2Chars.length;
                lcsLen > 0;) {
            if (s1Chars[i - 1] == s2Chars[j - 1]) {
                lcsChars[--lcsLen] = s1Chars[--i];
                j--;
            } else if (lcm[i - 1][j] > lcm[i][j-1]) i--;
            else j--;
        }
        return new String(lcsChars);
    }

    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
        LCSStringSolver solver = new LCSStringSolver(s1, s2);
        System.out.format("Single LCS of %s, %s = %s%n", s1, s2, solver.findSingleLCS());

        s1 = "ABCD";
        s2 = "ACBAD";
        solver = new LCSStringSolver(s1, s2);
        System.out.format("LCS of %s, %s = %s%n", s1, s2, solver.findAllLCS());

        s1 = "";
        s2 = "ACBAD";
        solver = new LCSStringSolver(s1, s2);
        System.out.format("All LCS of %s, %s = %s%n", s1, s2, solver.findAllLCS());
    }

}
