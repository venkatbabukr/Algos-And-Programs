package com.venkat.algos.dp.matrix;

public class LCSubStringSolver {

    protected char[] s1Chars;
    protected char[] s2Chars;
    protected int[][] lcssm;

    public LCSubStringSolver(String s1, String s2) {
        if (s1 == null || s2 == null)
            throw new IllegalArgumentException("Both s1 and s2 should be supplied!");
        this.s1Chars = s1.toCharArray();
        this.s2Chars = s2.toCharArray();
        this.lcssm = buildLCSSM(this.s1Chars, this.s2Chars);
    }

    private int[][] buildLCSSM(char[] s1Chars, char[] s2Chars) {
        int[][] lcssm = new int[s1Chars.length + 1][s2Chars.length + 1];

        // First fills
        for (int i = 1 ; i < s1Chars.length + 1 ; i++) lcssm[i][0] = 0;
        for (int j = 0 ; j < s2Chars.length + 1 ; j++) lcssm[0][j] = 0;

        // Remaining matrix
        for (int i = 1 ; i < s1Chars.length + 1 ; i++) {
            for (int j = 1 ; j < s2Chars.length + 1 ; j++) {
                lcssm[i][j] = (s1Chars[i - 1] == s2Chars[j - 1]) ? lcssm[i-1][j-1] + 1 : 0;
            }
        }
        return lcssm;
    }

    /**
     * O(mn) DP algo
     * Source - https://www.interviewbit.com/blog/longest-common-substring/
     * 
     * @return The longest common substring of the given two strings
     */
    public String findLCSubString() {
        // Now find the max in array
        int longestSubStrLen = 0, s1i = 0;
        for (int i = 0 ; i < lcssm.length; i++) {
            for (int j = 0 ; j < lcssm[i].length; j++) {
                if (lcssm[i][j] > longestSubStrLen) {
                    longestSubStrLen = lcssm[i][j];
                    s1i = i;
                }
            }
        }
        String lcSubStr = new String(s1Chars, s1i - longestSubStrLen, longestSubStrLen);
        return lcSubStr;
    }

    public static void main(String[] args) {
        String s1 = "";
        String s2 = "";
        LCSubStringSolver solver = new LCSubStringSolver(s1, s2);
        System.out.format("Longest common substring of %s & %s is %s%n", s1, s2, solver.findLCSubString());
        
        s1 = "Venkat";
        s2 = "kataradad";
        solver = new LCSubStringSolver(s1, s2);
        System.out.format("Longest common substring of %s & %s is %s%n", s1, s2, solver.findLCSubString());
    }

}
