package com.venkat.algos.simple.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.venkat.utils.ext.BooleanExt;

/**
 * Hackerrank test...
 * 
 * @author vbkomarl
 *
 */
public class MaxVowelsSubstringFinder {

	private boolean isVowel(char c) {
		c = Character.toLowerCase(c);
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}

    /*
    private int isVowel(char c) {
        switch (c) {
            case 'a': case 'e': case 'i': case 'o': case 'u':
            case 'A': case 'E': case 'I': case 'O': case 'U':
                return 1;
            default:
                return 0;
        }
    }
    */

    public List<String> maxVowelsSubstrImproved(String s, int substrLen) {
        if (s == null)
            throw new IllegalArgumentException("String required!");
        if (substrLen <= 0)
            throw new IllegalArgumentException("Invalid substring length!");

        List<String> maxVowelsSubstrs = new ArrayList<>();
        if (substrLen < s.length()) {
            char[] sChars = s.toCharArray();
            int currentWindowVowelsCount, maxVowelsCount;
            int windowStart, windowEnd;
            for (windowStart = windowEnd = currentWindowVowelsCount = maxVowelsCount = 0 ;
                    windowEnd < substrLen ; windowEnd++) {
                currentWindowVowelsCount += BooleanExt.toInt(isVowel(sChars[windowEnd]));
            }
            maxVowelsCount = currentWindowVowelsCount;
            if (currentWindowVowelsCount > 0) {
                maxVowelsSubstrs = new ArrayList<>();
                maxVowelsSubstrs.add(new String(sChars, windowStart, substrLen));
            }

            while (windowEnd < sChars.length) {
                currentWindowVowelsCount += (BooleanExt.toInt(isVowel(sChars[windowEnd++]))
                                              - BooleanExt.toInt(isVowel(sChars[windowStart++])));
                if (currentWindowVowelsCount >= maxVowelsCount) {
                	if (currentWindowVowelsCount > maxVowelsCount) {
                        maxVowelsSubstrs = new ArrayList<>();
                        maxVowelsCount = currentWindowVowelsCount;
                	}
                    maxVowelsSubstrs.add(new String(sChars, windowStart, substrLen));
                }
            }
        } else {
            for (char c : s.toCharArray()) {
                if (isVowel(c)) {
                    maxVowelsSubstrs = Arrays.asList(s);
                    break;
                }
            }
        }
        return maxVowelsSubstrs;
    }

    public String maxVowelsSubstr(String s, int substrLen) {
        if (s == null)
            throw new IllegalArgumentException("String required!");
        if (substrLen <= 0)
            throw new IllegalArgumentException("Invalid substring length!");
        String requiredSubstr = null;
        if (substrLen <= s.length()) {
            int[] vowelsMapping = new int[s.length()];
            for (int i = 0 ; i < s.length() ; i++) {
                vowelsMapping[i] = BooleanExt.toInt(isVowel(s.charAt(i)));
            }
            int maxVowelCount = 0;
            int maxVowelIdx = 0;
            int idx = 0;
            int vowelCount = 0;
            for (idx = 0; idx < substrLen; idx++)
                vowelCount += vowelsMapping[idx];
            maxVowelCount = vowelCount;
            for (; idx < vowelsMapping.length && vowelCount < substrLen; idx++) {
                vowelCount += vowelsMapping[idx];
                vowelCount -= vowelsMapping[idx - substrLen];
                if (vowelCount > maxVowelCount) {
                    maxVowelCount = vowelCount;
                    maxVowelIdx = idx - substrLen + 1;
                }
            }
            if (maxVowelCount > 0) {
                requiredSubstr = s.substring(maxVowelIdx, maxVowelIdx + substrLen);
            }
        }
        return requiredSubstr;
    }

    public static void main(String[] args) {
        class MaxVowelsSubstringFinderTestRecord {
            String s;
            int substrLen;

            public MaxVowelsSubstringFinderTestRecord(String s, int subLen) {
                this.s = s;
                this.substrLen = subLen;
            }
        }
        MaxVowelsSubstringFinderTestRecord[] allTests = new MaxVowelsSubstringFinderTestRecord[] {
            new MaxVowelsSubstringFinderTestRecord("xzdeiitn", 5),
            new MaxVowelsSubstringFinderTestRecord("AEiou", 5),
            new MaxVowelsSubstringFinderTestRecord("AEiou", 3)
        };

        MaxVowelsSubstringFinder finder = new MaxVowelsSubstringFinder();
        for (MaxVowelsSubstringFinderTestRecord testRecord : allTests) {
        	System.out.format("String=%s, Substrlen=%d, Max vowels substr=%s, All max vowels substrs=%s%n",
                                  testRecord.s,
                                  testRecord.substrLen,
                                  finder.maxVowelsSubstr(testRecord.s, testRecord.substrLen),
                                  finder.maxVowelsSubstrImproved(testRecord.s, testRecord.substrLen));
        }
    }

}
