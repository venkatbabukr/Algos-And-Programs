package com.venkat.utils.ext;

import java.util.List;

import com.venkat.algos.simple.strings.StringAlgos;

public final class StringExt {

    public static final String EMPTY_STR = "";

    private StringExt() { }

	public static boolean isNonEmpty(String s) {
	    return s != null && !s.isEmpty();
	}

    public static boolean isPalindrome(String s) {
    	return StringAlgos.isPalindrome(s);
    }

    public static Character findFirstNonRepeatingChar(String s) {
    	return StringAlgos.findFirstNonRepeatingChar(s);
    }

    public static String firstLongestNonRepeatingSubStr(String s) {
    	return StringAlgos.firstLongestNonRepeatingSubStr(s);
    }

    public static List<String> allLongestNonRepeatingSubStr(String s) {
        return StringAlgos.allLongestNonRepeatingSubStr(s);
    }

    public static boolean containsSubStrSeq(String str, String subStr) {
    	return StringAlgos.containsSubStrSeq(str, subStr);
    }

}
