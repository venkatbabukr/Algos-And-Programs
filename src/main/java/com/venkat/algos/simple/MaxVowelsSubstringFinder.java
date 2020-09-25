package com.venkat.algos.simple;

/**
 * Hackerrank test...
 * 
 * @author vbkomarl
 *
 */
public class MaxVowelsSubstringFinder {

    private int isVowel(char c) {
        switch (c) {
            case 'a': case 'e': case 'i': case 'o': case 'u':
            case 'A': case 'E': case 'I': case 'O': case 'U':
                return 1;
            default:
                return 0;
        }
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
                vowelsMapping[i] = isVowel(s.charAt(i));
            }
            int maxVowelCount = 0;
            int maxVowelIdx = 0;
            int idx = 0;
            int vowelCount = 0;
            for (idx = 0; idx < substrLen; idx++)
                vowelCount += vowelsMapping[idx];
            maxVowelCount = vowelCount;
            for (; idx < vowelsMapping.length; idx++) {
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
        System.out.println(new MaxVowelsSubstringFinder().maxVowelsSubstr("xzdeiitn", 5));
    }
}
