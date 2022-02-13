package com.venkat.algos.dp.matrix.more;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.venkat.algos.dp.matrix.LCSStringSolver;
import com.venkat.utils.ArraysExt;

public class LCSStringSolverSpaceOptimized extends LCSStringSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(LCSStringSolverSpaceOptimized.class);

	public LCSStringSolverSpaceOptimized(String s1, String s2) {
		super(s1, s2);
	}

	@Override
    protected int[][] buildLCM(char[] s1Chars, char[] s2Chars) {
        int [][] lcm = null;
        if (s1Chars.length > 0 && s2Chars.length > 0) {
            lcm = new int[s1Chars.length][s2Chars.length];
            // Initialize lcm array...
            lcm[0][0] = s1Chars[0] == s2Chars[0] ? 1 : 0;
            for (int j = 1 ; j < lcm[0].length ; j++) lcm[0][j] = s1Chars[0] == s2Chars[j] ? 1 : lcm[0][j - 1];
            for (int i = 1 ; i < lcm.length ; i++) lcm[i][0] = s1Chars[i] == s2Chars[0] ? 1 : lcm[i - 1][0];

            // Remaining matrix fill...
            for (int i = 1; i < s1Chars.length; i++) {
                for (int j = 1; j < s2Chars.length; j++) {
                    lcm[i][j] = s1Chars[i] == s2Chars[j] ?
                                    1 + lcm[i - 1][j - 1] :
                                            Math.max(lcm[i - 1][j], lcm[i][j - 1]);
                }
            }
            LOGGER.debug("LCS CountMatrix:\n{}\n", ArraysExt.to2DString(lcm));
        }
        return lcm;
    }

	@Override
    public String findSingleLCS() {
		char[] lcsChars = null;
		if (lcm != null) {
	    	int lcsLen = lcm[s1Chars.length - 1][s2Chars.length - 1];
	    	lcsChars = new char[lcsLen];
	    	for (int i = s1Chars.length - 1, j = s2Chars.length - 1, lcsIdx = lcsLen - 1; lcsIdx >= 0;) {
	    		if (s1Chars[i] == s2Chars[j]) {
	    			lcsChars[lcsIdx] = s1Chars[i];
	    			lcsIdx--; i--; j--;
	    		} else if ((i > 0 ? lcm[i - 1][j] : 0) > (j > 0 ? lcm[i][j - 1] : 0)) {
	    			i--;
	    		} else
	    			j--;
	    	}
		}
    	return Optional.ofNullable(lcsChars)
                       .map(chars -> new String(chars))
                       .orElse(null);
    }

	@Override
	public List<String> findAllLCS() {
		return Collections.emptyList();
	}

    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
        LCSStringSolver solver = new LCSStringSolverSpaceOptimized(s1, s2);
        System.out.format("Single LCS of %s, %s = %s%n", s1, s2, solver.findSingleLCS());

        s1 = "ABCD";
        s2 = "ACBAD";
        solver = new LCSStringSolverSpaceOptimized(s1, s2);
        System.out.format("Single LCS of %s, %s = %s%n", s1, s2, solver.findSingleLCS());

        s1 = "";
        s2 = "ACBAD";
        solver = new LCSStringSolverSpaceOptimized(s1, s2);
        System.out.format("Single LCS of %s, %s = %s%n", s1, s2, solver.findSingleLCS());
    }

}
