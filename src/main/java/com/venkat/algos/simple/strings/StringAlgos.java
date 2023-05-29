package com.venkat.algos.simple.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.venkat.utils.Pair;
import com.venkat.utils.ext.ObjectsExt;
import com.venkat.utils.ext.StringExt;

public class StringAlgos {

    /* Single string algos */
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

    public static boolean isPanagram(String s) {
        int numDistinctAlphabetsInStr = 0;
        if (StringExt.isNonEmpty(s)) {
            boolean[] alphabetPresent = new boolean[26];
            Arrays.fill(alphabetPresent, false);
            char[] sChars = s.toLowerCase().toCharArray();
            for (int i = 0 ; i < sChars.length && numDistinctAlphabetsInStr < 26 ; i++) {
                int currCharAplhabetIdx = sChars[i] - 'a';
                if (currCharAplhabetIdx > -1 && currCharAplhabetIdx < 26 && !alphabetPresent[currCharAplhabetIdx]) {
                    alphabetPresent[currCharAplhabetIdx] = true;
                    numDistinctAlphabetsInStr++;
                }
            }
        }
        return numDistinctAlphabetsInStr == 26;
    }

    public static boolean isPanagramEnhanced(String s) {
    	boolean stringIsPanagram = false;
    	if (StringExt.isNonEmpty(s) && s.length() > 25) {
    		int panagramCheckBits = 1 << 26 - 1;
    		char[] sChars = s.toCharArray();
    		for (int i = 0 ; i < sChars.length && panagramCheckBits > 0 ; i++) {
    			panagramCheckBits &= ~(1 << sChars[i] - 'a');
    		}
    		stringIsPanagram = panagramCheckBits == 0;
    	}
    	return stringIsPanagram;
    }

    public static int parseInt(String str) {
        if (StringExt.isNonEmpty(str)) {
            int intVal = 0;
            char[] strChars = str.toCharArray();
            int idx = 0;
            int pnMultiplier = 1;
            switch (strChars[idx]) {
                case '-':
                    pnMultiplier = -1;
                case '+':
                    idx++;
            }
            if (idx >= strChars.length)
                throw new NumberFormatException("Number missing! Looks like only signs +/- are given");
            for (; idx < strChars.length ; idx++) {
                int digitVal = Character.digit(strChars[idx], 10);
                if (digitVal == -1)
                    throw new NumberFormatException("Invalid digit found!");
                intVal = Math.addExact(Math.multiplyExact(intVal, 10),
                             pnMultiplier * digitVal);
            }
            return intVal;
        } else {
            throw new NumberFormatException("Can't give null or empty string! " + str);
        }
    }

    public static Map<Character, Integer> getCharacterCountMap(String s) {
        Map<Character, Integer> charsCountMap = null;
        if (StringExt.isNonEmpty(s)) {
            charsCountMap = s.chars()
                              .boxed()
                              .collect(Collectors.groupingBy(c -> new Character((char) c.intValue()),
                                                             Collectors.collectingAndThen(Collectors.counting(),
                                                                                          count -> count.intValue())));
        }
        return charsCountMap;
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

    public static Character findFirstRepeatingChar(String s) {
        Character firstRepeatingChar = null;
        if (StringExt.isNonEmpty(s)) {
            char[] sChars = s.toCharArray();
            Set<Character> foundChars = new HashSet<>();
            for (int i = 0; firstRepeatingChar == null && i < sChars.length; i++) {
                if (foundChars.contains(sChars[i])) {
                    firstRepeatingChar = sChars[i];
                } else {
                    foundChars.add(sChars[i]);
                }
            }
        }
        return firstRepeatingChar;
    }

    /**
     * The problem is to find first longest substring substr within s such that substr doesn't contain any
     * repeating characters!
     * 
     * @param s       Input string
     * @return        Longest substr of s such that substr doesn't have any repeating characters!
     */
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

    /* Two string algos */
    public static Map<Character, Character> getIsomorphicMap(String s1, String s2) {
        Map<Character, Character> isoCharsMap = null;
        if (s1 != null && s2 != null && s1.length() == s2.length()) {
            isoCharsMap = new LinkedHashMap<>();
            Set<Character> isoMappedChars = new HashSet<>();
            char[] s1Chars = s1.toCharArray();
            char[] s2Chars = s2.toCharArray();
            for (int i = 0 ; i < s1Chars.length && isoCharsMap != null ; i++) {
                Character c = s1Chars[i];
                Character cIso = isoCharsMap.get(c);
                if (cIso == null || s2Chars[i] != cIso) {
                    cIso = s2Chars[i];
                    if (!isoMappedChars.contains(cIso)) {
                        isoCharsMap.put(c, cIso);
                        isoMappedChars.add(cIso);
                    } else {
                        isoCharsMap = null;
                    }
                }
            }
        }
        return isoCharsMap;
    }

    public static boolean areIsomorphic(String s1, String s2) {
        return getIsomorphicMap(s1, s2) != null;
    }

    public static boolean areAnagrams(String s1, String s2) {
        boolean stringsAreAnagrams = false;
        if (s1 != null && s2 != null && s1.length() == s2.length()) {
            char[] s1Chars = s1.toCharArray();
            char[] s2Chars = s2.toCharArray();
            Arrays.sort(s1Chars);
            Arrays.sort(s2Chars);
            stringsAreAnagrams = Arrays.equals(s1Chars, s2Chars);
        }
        return stringsAreAnagrams || (s1 == null && s2 == null);
    }

    public static boolean areAnagramsEnhanced(String s1, String s2) {
        return (s1 == null && s2 == null) ||
            ObjectsExt.nullSafeEquals(getCharacterCountMap(s1), getCharacterCountMap(s2));
    }

    public static boolean areKAnagrams(String s1, String s2, int k) {
        boolean stringsAreKAnagrams = false;
        if (s1 != null && s2 != null && s1.length() == s2.length()) {
            char[] s1Chars = s1.toCharArray();
            char[] s2Chars = s2.toCharArray();
            Arrays.sort(s1Chars);
            Arrays.sort(s2Chars);
            int diffIdx = 0;
            for (diffIdx = 0; diffIdx < s1Chars.length && s1Chars[diffIdx] == s2Chars[diffIdx] ; diffIdx++);
            int numDifferingChars = s1Chars.length - diffIdx;
            stringsAreKAnagrams = numDifferingChars == 0 || (numDifferingChars <= k &&
                                      ((s1Chars[diffIdx-1] != s1Chars[diffIdx] && getIsomorphicMap(s1.substring(diffIdx), s2.substring(diffIdx)) != null) ||
                                       (s2Chars[diffIdx-1] != s2Chars[diffIdx] && getIsomorphicMap(s2.substring(diffIdx), s1.substring(diffIdx)) != null)));
        }
        return stringsAreKAnagrams || (s1 == null && s2 == null);
    }

    public static boolean areKAnagramsEnhanced(String s1, String s2, int k) {
        boolean stringsAreKAnagrams = false;
        if (s1 != null && s2 != null && s1.length() == s2.length()) {
            Map<Character, Integer> s1CharCounts = getCharacterCountMap(s1);
            Map<Character, Integer> s2CharCounts = getCharacterCountMap(s2);
            int charCountsDiff = 0;
            for (Iterator<Entry<Character, Integer>> s1CCIter = s1CharCounts.entrySet().iterator();
                s1CCIter.hasNext() && charCountsDiff < k + 1;) {
                Entry<Character, Integer> e = s1CCIter.next();
                Character compareChar = e.getKey();
                Integer compareCharS1Count = e.getValue();
                Integer compareCharS2Count = s2CharCounts.get(compareChar);
                if (!ObjectsExt.nullSafeEquals(compareCharS1Count, compareCharS2Count))
                    charCountsDiff++;
            }
            stringsAreKAnagrams = charCountsDiff <= k;
        }
        return stringsAreKAnagrams || (s1 == null && s2 == null);
    }

    public static boolean containsSubStrSeq(String mainStr, String subStr) {
        if (mainStr == null || subStr == null) {
            throw new IllegalArgumentException("Main string and substring needs to be given!");
        }

        int subStrScanCharIdx = 0;
        if (subStr.length() > 0) {
            char[] subStrChars = subStr.toCharArray();
            for (char c : mainStr.toCharArray()) {
                if (c == subStrChars[subStrScanCharIdx]) {
                    subStrScanCharIdx++;
                }
                if (subStrScanCharIdx == subStrChars.length) break;
            }
        }
        return subStrScanCharIdx == subStr.length();
    }

    public static List<List<String>> allAnagramGroups(List<String> wordsList) {
        if (wordsList == null || wordsList.isEmpty())
            throw new IllegalArgumentException("Words list can't be null or empty!");
        Map<Map<Character, Integer>, List<String>> wordsGroupByCharCounts =
            wordsList.stream()
                     .collect(Collectors.groupingBy(StringAlgos::getCharacterCountMap,
                                                    LinkedHashMap::new,
                                                    Collectors.toList()));
        return wordsGroupByCharCounts.entrySet()
                                     .stream()
                                     .map(e -> e.getValue())
                                     .collect(Collectors.toList());
    }

    public static List<List<String>> allAnagramGroups(String[] wordsArr) {
        return allAnagramGroups(Arrays.asList(wordsArr));
    }

    /*
     * https://www.geeksforgeeks.org/minimum-distance-between-words-of-a-string/
     */
    public static int closestDistance(String[] words, String w1, String w2) {
        int closestDist = -1;
        if (w1.equals(w2)) {
            closestDist = 0;
        } else {
            int w1Idx = -1, w2Idx = -1;
            for (int idx = 0 ; idx < words.length ; idx++) {
                if (w1.equals(words[idx])) {
                    if (w2Idx == -1) {
                        w1Idx = idx;
                    } else {
                        closestDist = idx - w2Idx;
                        break;
                    }
                } else if (w2.equals(words[idx])) {
                    if (w1Idx == -1) {
                        w2Idx = idx;
                    } else {
                        closestDist = idx - w1Idx;
                        break;
                    }
                }
            }
        }
        return closestDist;
    }

    public static void main(String[] args) {
        String[] palindromeTestCases = new String[] {
            null,
            "A",
            "madam",
            "Madam"
        };
        for (String s : palindromeTestCases) {
            System.out.format("isPalindrome(%s)=%s%n", s, isPalindrome(s));
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
            System.out.format("findFirstNonRepeatingChar(%s)=%c%n", s, findFirstNonRepeatingChar(s));
        }

		String[] panagramTestCases = new String[] {
            null,
            "string",
            "a",
            "aaaccd",
            "azbcdefghijklmnopqrstuvwxy",
            "aabcdefghijklmnopqrstuvwxy",
            "aabcdefghijklmnopqrstuvwwxyzz" };
		for (String s : panagramTestCases) {
			System.out.format("panagramTestCases(%s)=%s%n", s, isPanagramEnhanced(s));
		}

        List<Pair<String>> isomorphicStrsTestCases = Arrays.asList(
            new Pair<String>("xxy", "aab"),
            new Pair<String>("xyz", "aab"),
            new Pair<String>("ACAB", "XCXY")
        );
        for (Pair<String> testCase : isomorphicStrsTestCases) {
            String s1 = testCase.getX();
            String s2 = testCase.getY();
            System.out.format("getIsomorphicMap(%s, %s)=%s%n", s1, s2, getIsomorphicMap(s1, s2));
        }

        List<String[]> allAnagramGroupsTestCases = Arrays.asList(
            new String[] {"act","god","cat","dog","tac"},
            new String[] {"no", "on", "is"}
        );
        for (String[] testCase : allAnagramGroupsTestCases) {
            System.out.format("allAnagramGroups(%s)=%s%n", Arrays.toString(testCase), allAnagramGroups(testCase));
        }

        String[] allParseIntTestCases = new String[] {
            String.valueOf(Integer.MIN_VALUE) + "5",
            String.valueOf(Integer.MAX_VALUE) + "5",
            String.valueOf(Integer.MIN_VALUE),
            String.valueOf(Integer.MAX_VALUE)
        };
        for (String s : allParseIntTestCases) {
            try {
                System.out.format("parseInt(\"%s\")=%d%n", s, parseInt(s));
            } catch (RuntimeException re) {
                System.out.format("Exception %s for parseInt(\"%s\")%n", re.getMessage(), s);
            }
        }
    }

}
