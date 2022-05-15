package com.venkat.algos.simple.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.venkat.utils.Pair;
import com.venkat.utils.ext.StringExt;

public class KMPPattern {

    private char[] pChars;

    /**
     * pi table will have the skipIndex for each character.
     * The skipIndex will be the idces that can be skipped as there exists a prefix pattern
     * equal to the current sequence.
     * 
     * Ex: pi([a  b  a  b  c  d  a  b  a  b  e])=
     *        [0, 0, 1, 2, 0, 0, 1, 2, 3, 4, 0]
     */
    private int[] pi;

    class Matcher {
    	private char[] sChars;
    	private int matchStart;
    	private int matchEnd;

        public Matcher(String s) {
        	matchStart = 0;
        	matchEnd = -1;
        	sChars = StringExt.isNonEmpty(s) ? s.toCharArray() : null;
        }

        public boolean findNextMatch() {
        	boolean matchFound = false;
        	if (sChars != null && matchStart < sChars.length) {
        		int i = matchEnd + 1, j = 0;
        		for (; i < sChars.length && j < pChars.length; i++, j++) {
        			while (sChars[i] != pChars[j] && j > 0) {
                        j = pi[j-1];
        			}
        			if (j == 0 && sChars[i] != pChars[j]) j--;
        		}
        		matchFound = j == pChars.length;
        		if (matchFound) {
        			matchEnd = i;
        			matchStart = i - pChars.length;
        		}
        	}
        	return matchFound;
        }

        public boolean matches() {
            return matchStart < matchEnd || findNextMatch();
        }

        public int getMatchStart() throws IllegalStateException {
        	if (!matches())
                throw new IllegalStateException("No match found for pattern!");
        	return matchStart;
        }

        public int getMatchEnd() throws IllegalStateException {
        	if (!matches())
                throw new IllegalStateException("No match found for pattern!");
        	return matchEnd;
        }

    }

    public KMPPattern(String pattern) {
        if (!StringExt.isNonEmpty(pattern)) {
        	throw new IllegalArgumentException("Matching pattern can't be null or empty!");
        }
        pChars = pattern.toCharArray();
        pi = new int[pChars.length];
        pi[0] = 0;
        for (int i = 1; i < pi.length; i++) {
        	// if pi[i-1] == 0, pChars[i] will be compared with pChars[0] (The first character - to see if we can start the prefix sequence)
        	// else pChars[i] will be compared with pChars[pi[i-1]] (The char next to prefix we have found till now)!
        	pi[i] = pChars[i] == pChars[pi[i-1]] ? pi[i-1] + 1 : 0;
        }
    }

    public Matcher getStringMatcher(String s) {
    	return new Matcher(s);
    }

    public static KMPPattern compile(String pattern) {
    	return new KMPPattern(pattern);
    }

    public static void main(String[] args) {
    	List<Pair<String>> allMatchingTests = Arrays.asList(
            new Pair<>("ababd", "ababcabcabababd"),
            new Pair<>("geek", "geeksforgeeks"));

        for (Pair<String> testPair : allMatchingTests) {
        	String searchPattern = testPair.getX();
        	KMPPattern sp = KMPPattern.compile(searchPattern);
        	System.out.format("Search pattern=%s, piTable=%s%n", searchPattern, Arrays.toString(sp.pi));
        	String str = testPair.getY();

        	List<Integer> matchPositions = new ArrayList<>();
            for (Matcher spMatcher = sp.getStringMatcher(str); spMatcher.findNextMatch();) {
        		matchPositions.add(spMatcher.getMatchStart());
            }
        	System.out.format("String=%s, Pattern %s %s!%n", str, searchPattern,
                                  matchPositions.isEmpty() ? "not found" : "found @ " + matchPositions);
        }
    }
}
