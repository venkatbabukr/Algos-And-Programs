package com.venkat.algos.dp.matrix.more;

import java.util.Arrays;

import com.venkat.algos.dp.matrix.LCSubStringSolver;

public class LCSubStringSolverExtendedJava8 extends LCSubStringSolver {

	public LCSubStringSolverExtendedJava8(String s1, String s2) {
		super(s1, s2);
	}

	@Override
    public String findLCSubString() {
        // Find the max in array
        int longestSubStrLen = 0, s1i = 0;

        Arrays.stream(lcssm).map(row -> Arrays.stream(row).max().orElse(0));
        return null;
    }

}
