package com.venkat.algos.simple.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.venkat.utils.StringExt;

public class StringAlgos {

    public static boolean isPalindrome(String s) {
        boolean strIsPalindrome = false;
        if (StringExt.isNonEmpty(s)) {
            char[] sChars = s.toLowerCase().toCharArray();
            int l = 0, r = sChars.length - 1;
            for (;l < r && sChars[l] == sChars[r]; l++, r--);
            strIsPalindrome = l >= r;
        }
        return strIsPalindrome;
    }

    public static Character findFirstNonRepeatingChar(String s) {
        Character firstNonRepeatingChar = null;
        if(StringExt.isNonEmpty(s)) {
            char[] sChars = s.toCharArray();
            LinkedHashSet<Character> nonRepeatingChars = new LinkedHashSet<>();
            Set<Character> repeatingChars = new HashSet<>();
            for (char c : sChars) {
                if (!repeatingChars.contains(c)) {
                    if (nonRepeatingChars.remove(c)) {
                        repeatingChars.add(c);
                    } else {
                        nonRepeatingChars.add(c);
                    }
                }
            }
            if (!nonRepeatingChars.isEmpty()) {
                firstNonRepeatingChar = nonRepeatingChars.iterator().next();
            }
        }
        return firstNonRepeatingChar;
    }

    public static String firstLongestNonRepeatingSubStr(String s) {
        String longestSubStr = s;
        if (StringExt.isNonEmpty(s)) {
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

    private static List<String> checkAndAddToLongestNonRepeatingSubStrs(List<String> longestSubStrs, char[] sChars, int scanBegin, int scanLen, int longestLen) {
        if (scanLen >= longestLen) {
            String newLongestSubStr = new String(sChars, scanBegin, scanLen);
            if (scanLen > longestLen) {
                longestSubStrs = new ArrayList<>();
            }
            longestSubStrs.add(newLongestSubStr);
        }
        return longestSubStrs;
    }

    public static List<String> allLongestNonRepeatingSubStr(String s) {
        List<String> allLongestNRSS = s == null ? null : new ArrayList<>();
        if (StringExt.isNonEmpty(s)) {
            int longestLen = 0;
            Map<Character, Integer> charIdces = new HashMap<>();
            char[] sChars = s.toCharArray();
            for (int scanBegin = 0, scanEnd = 0, scanLen = 1; scanEnd < sChars.length; scanEnd++, scanLen++) {
                char c = sChars[scanEnd];
                Integer cPrevIdx = charIdces.get(c);
                if (cPrevIdx != null && cPrevIdx >= scanBegin) {
                    allLongestNRSS = checkAndAddToLongestNonRepeatingSubStrs(allLongestNRSS, sChars, scanBegin, scanLen - 1, longestLen);
                    longestLen = Math.max(longestLen, scanLen - 1);
                    scanBegin = cPrevIdx + 1;
                    scanLen = scanEnd - scanBegin + 1;
                } else {
                    allLongestNRSS = checkAndAddToLongestNonRepeatingSubStrs(allLongestNRSS, sChars, scanBegin, scanLen, longestLen);
                    longestLen = Math.max(longestLen, scanLen);
                }
                charIdces.put(c, scanEnd);
            }
        }
        return allLongestNRSS;
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
            System.out.format("Str=%s, First longestNonRepeatingSubStr=%s, All longestNonRepeatingSubStr=%s%n", s, firstLongestNonRepeatingSubStr(s), allLongestNonRepeatingSubStr(s));
        }

        String[] firstNonRepeatingCharTestCases = new String[] {
            null,
            "string",
            "a",
            "aaaccd",
            "aaabccdee",
            "aabcdbac",
            "aabbcc"
        };
        for (String s : firstNonRepeatingCharTestCases) {
        	System.out.format("Str=%s, First non repeating char=%c%n", s, findFirstNonRepeatingChar(s));
        }
    }

}
