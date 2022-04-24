package com.venkat.algos.simple;

import java.util.HashMap;
import java.util.Map;

public class StringExt {

    public static boolean isNonEmpty(String s) {
        return s != null && !s.isEmpty();
    }

    public static boolean isPalindrome(String s) {
        boolean strIsPalindrome = false;
        if (s != null && s.length() > 0) {
            char[] sChars = s.toLowerCase().toCharArray();
            int l = 0, r = sChars.length - 1;
            for (;l < r && sChars[l] == sChars[r]; l++, r--);
            strIsPalindrome = l >= r;
        }
        return strIsPalindrome;
    }

    public static String longestNonRepeatingSubStr(String s) {
        String longestSubStr = s;
        if (isNonEmpty(s)) {
            int longestBegin = 0, longestEnd = 0;
            Map<Character, Integer> charIdces = new HashMap<>();
            char[] sChars = s.toCharArray();
            for (int scanBegin = 0, scanEnd = 0; scanEnd < sChars.length; scanEnd++) {
                char c = sChars[scanEnd];
                Integer cPrevIdx = charIdces.get(c);
                if (cPrevIdx != null && cPrevIdx >= scanBegin) {
                    if (scanEnd - scanBegin - 1 > longestEnd - longestBegin) {
                    	longestBegin = scanBegin;
                    	longestEnd = scanEnd;
                    }
                    scanBegin = cPrevIdx + 1;
                } else if (scanEnd - scanBegin + 1 > longestEnd - longestBegin) {
                    longestBegin = scanBegin;
                    longestEnd = scanEnd;
                }
                charIdces.put(c, scanEnd);
            }
            longestSubStr = s.substring(longestBegin, longestEnd + 1);
        }
        return longestSubStr;
    }

    public static void main(String[] args) {
        String[] palindromeTestCases = new String[] {
            null,
            "A",
            "madam",
            "Madam"
        };
        for (String s : palindromeTestCases) {
            System.out.format("Str=%s, isPalindrome=%s%n", s, isPalindrome(s));
        }

        String[] longestNonRepeatingSubStrTestCases = new String[] {
                null,
                "A",
                "BBBB",
                "GEEKFORGEEKS",
                "ABDEFGABEF",
                "ABCDEF"
        };
        for (String s : longestNonRepeatingSubStrTestCases) {
            System.out.format("Str=%s, longestNonRepeatingSubStr=%s%n", s, longestNonRepeatingSubStr(s));
        }

    }

}
