package com.venkat.algos.dp.lcs;

public class LCSubStringSolver {

	private char[] s1Chars;
	private char[] s2Chars;

	public LCSubStringSolver(String s1, String s2) {
		this.s1Chars = s1.toCharArray();
		this.s2Chars = s2.toCharArray();
	}

    /**
     * O(mn) DP algo
     * Source - https://www.interviewbit.com/blog/longest-common-substring/
     * 
     * @return The longest common substring of the given two strings
     */
	public String findLCSubString() {
		String lcSubStr = null;
        if (s1Chars != null && s1Chars.length > 0 && s2Chars != null && s2Chars.length > 0) {
            int[][] lcsstrmatrix = new int[s1Chars.length][s2Chars.length];

            // First fills
            lcsstrmatrix[0][0] = s1Chars[0] == s2Chars[0] ? 1 : 0;
            for (int i = 1 ; i < s1Chars.length ; i++) {
            	lcsstrmatrix[i][0] = (s1Chars[i] == s2Chars[0]) ? 1 : 0;
            }
            for (int j = 1 ; j < s2Chars.length ; j++) {
            	lcsstrmatrix[0][j] = (s2Chars[j] == s1Chars[0]) ? 1 : 0;
            }

            // Remaining matrix
            for (int i = 1 ; i < s1Chars.length ; i++) {
            	for (int j = 1 ; j < s2Chars.length ; j++) {
            		lcsstrmatrix[i][j] = (s1Chars[i] == s2Chars[j]) ? lcsstrmatrix[i-1][j-1] + 1 : 0;
            	}
            }
            
            // Now find the max in array
            int longestLen = 0, s1i = 0;
            for (int i = 0 ; i < lcsstrmatrix.length; i++) {
            	for (int j = 0 ; j < lcsstrmatrix[i].length; j++) {
            		if (lcsstrmatrix[i][j] > longestLen) {
            			longestLen = lcsstrmatrix[i][j];
            			s1i = i;
            		}
            	}
            }
            if (longestLen > 0) {
            	lcSubStr = new String(s1Chars, s1i - longestLen + 1, longestLen);
            }
        }
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
